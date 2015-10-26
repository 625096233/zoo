package zoo.zuul.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author w.rittscher
 * @since 23.10.2015
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureUser(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("user").password("user").roles("USER")
				.and()
			.withUser("admin").password("admin").roles("USER", "ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		HttpSessionCsrfTokenRepository tokenRepository = new HttpSessionCsrfTokenRepository();
		tokenRepository.setHeaderName("X-XSRF-TOKEN");
		http
			.httpBasic().disable()
			.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/security/authenticate")
				.successHandler((request, response, authentication) -> response.setStatus(HttpServletResponse.SC_ACCEPTED))
				.failureHandler((request, response, exception) -> response.setStatus(HttpServletResponse.SC_UNAUTHORIZED))
				.permitAll()
			.and()
			.exceptionHandling()
					.authenticationEntryPoint((request2, response2, authException) -> response2.setStatus(HttpServletResponse.SC_UNAUTHORIZED))
			.and()
			.logout()
				.logoutUrl("/security/logout")
				.logoutSuccessHandler((request, response, authentication1) -> response.setStatus(HttpServletResponse.SC_OK))
				.deleteCookies("JSESSIONID")
				.permitAll()
			.and()
			.csrf()
				.csrfTokenRepository(tokenRepository)
			.and()
				.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
			.authorizeRequests()
				.antMatchers("/", "/login").permitAll()
				.anyRequest().authenticated();
	}

	static class CsrfHeaderFilter extends OncePerRequestFilter {
		@Override
		protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
			CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
			if (csrf != null) {
				Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
				String token = csrf.getToken();
				if (cookie == null || token != null && !token.equals(cookie.getValue())) {
					cookie = new Cookie("XSRF-TOKEN", token);
					cookie.setPath("/");
					response.addCookie(cookie);
				}
			}
			filterChain.doFilter(request, response);
		}
	}
}
