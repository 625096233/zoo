package zoo.zuul.configuration;

import org.junit.Before;
import org.junit.Test;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.security.authentication.TestingAuthenticationToken;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author w.rittscher
 * @since 03.11.2015
 */
public class ServiceBaseAccessDecisionRoleVoterTest {

	private ServiceBaseAccessWebExpressionVoter serviceBaseAccessDecisionRoleVoter;

	@Before
	public void setUp() throws Exception {
		Map<String, ZuulProperties.ZuulRoute> routes = new HashMap<String, ZuulProperties.ZuulRoute>() {{
			put("panda", new ZuulProperties.ZuulRoute("/panda/**", "panda"));
			put("tiger", new ZuulProperties.ZuulRoute("/tiger/**", "tiger"));
		}};
		serviceBaseAccessDecisionRoleVoter = new ServiceBaseAccessWebExpressionVoter(routes);
	}

	@Test
	public void testName() throws Exception {
		TestingAuthenticationToken testingAuthenticationToken = new TestingAuthenticationToken(null, null, Arrays.asList());
		serviceBaseAccessDecisionRoleVoter.vote(null, null, null);


	}
}