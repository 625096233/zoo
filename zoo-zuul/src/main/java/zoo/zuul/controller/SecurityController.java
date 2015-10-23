package zoo.zuul.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;

/**
 * @author w.rittscher
 * @since 23.10.2015
 */
@RestController
@RequestMapping("security")
public class SecurityController {

	@RequestMapping("user")
	public Map<String, Object> user(Principal user) {
		return Collections.<String, Object> singletonMap("name", user.getName());
	}

}
