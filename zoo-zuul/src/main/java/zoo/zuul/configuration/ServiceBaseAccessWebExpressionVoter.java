package zoo.zuul.configuration;

import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;

import java.util.Collection;
import java.util.Map;

/**
 * @author w.rittscher
 * @since 03.11.2015
 */
public class ServiceBaseAccessWebExpressionVoter implements AccessDecisionVoter<FilterInvocation> {
	private final Map<String, ZuulProperties.ZuulRoute> routes;

	public ServiceBaseAccessWebExpressionVoter(Map<String, ZuulProperties.ZuulRoute> routes) {
		this.routes = routes;
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return (attribute.getAttribute() != null) && attribute.getAttribute().contains(":");
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	@Override
	public int vote(Authentication authentication, FilterInvocation filterInvocation, Collection<ConfigAttribute> attributes) {
		int result = ACCESS_ABSTAIN;
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		for (ConfigAttribute attribute : attributes) {
			if (this.supports(attribute)) {
				result = ACCESS_DENIED;

				// Attempt to find a matching granted authority
				for (GrantedAuthority authority : authorities) {
					if (attribute.getAttribute().equals(authority.getAuthority())) {
						return ACCESS_GRANTED;
					}
				}
			}
		}

		return result;
	}

}
