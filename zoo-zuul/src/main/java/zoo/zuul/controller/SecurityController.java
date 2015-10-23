package zoo.zuul.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;

/**
 * @author w.rittscher
 * @since 23.10.2015
 */
@Slf4j
@RestController
@RequestMapping("security")
public class SecurityController {

	@RequestMapping(value = "user", method = RequestMethod.GET)
	public Map<String, Object> getAuthentication(Principal user) {
		if (user != null) {
			return Collections.<String, Object> singletonMap("name", user.getName());
		}
		return null;
	}

}
