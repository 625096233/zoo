package zoo.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

/**
 * @author w.rittscher
 * @since 22.10.2015
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Bootstrap {

	public static void main(String[] args) {
		SpringApplication.run(Bootstrap.class, args);
	}

	@Configuration
	public static class SecurityConfig extends WebSecurityConfigurerAdapter {

		@Autowired
		private DatabaseUserDetailService userDetailService;

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.formLogin()
					.loginPage("/login")
					.defaultSuccessUrl("/login/successful")
					.permitAll()
				.and()
				.logout()
					.permitAll()
				.and()
				.authorizeRequests()
					.anyRequest().authenticated();
		}

		@Autowired
		public void configureGlobal(AuthenticationManagerBuilder auth, UserDetailsService accountUserDetailService) throws Exception {
			auth.userDetailsService(accountUserDetailService);
		}

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailService);
		}
	}

	@Configuration
	@EnableAuthorizationServer
	public static class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

		@Autowired
		private AuthenticationManager authenticationManager;

		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
			endpoints.authenticationManager(authenticationManager);
		}

		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			clients.inMemory()
					.withClient("ui")
					.secret("ui")
					.authorizedGrantTypes("authorization_code", "refresh_token").scopes("openid");
		}
	}

}
