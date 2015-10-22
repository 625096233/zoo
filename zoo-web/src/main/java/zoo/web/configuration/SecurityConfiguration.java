package zoo.web.configuration;

import org.springframework.cloud.security.oauth2.sso.OAuth2SsoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author w.rittscher
 * @since 22.10.2015
 */
@Configuration
public class SecurityConfiguration extends OAuth2SsoConfigurerAdapter {

	@Component
	@Order(Ordered.HIGHEST_PRECEDENCE)
	static class CorsFilter implements Filter {

		@Override
		public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
			HttpServletResponse response = (HttpServletResponse) res;
			HttpServletRequest request = (HttpServletRequest) req;
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
			response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
			response.setHeader("Access-Control-Max-Age", "3600");
			if (!request.getMethod().equals("OPTIONS")) {
				chain.doFilter(req, res);
			} else {}
		}

		public void init(FilterConfig filterConfig) {}

		public void destroy() {}

	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		HttpSessionCsrfTokenRepository tokenRepository = new HttpSessionCsrfTokenRepository();
		tokenRepository.setHeaderName("X-XSRF-TOKEN");

		http.csrf()
				.csrfTokenRepository(tokenRepository)
			.and()
				.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
			.logout()
				.permitAll()
			.and()
			.antMatcher("/**")
				.authorizeRequests()
					.antMatchers("/").permitAll()
					.antMatchers("/profile").authenticated()
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
