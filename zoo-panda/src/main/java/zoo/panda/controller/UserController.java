package zoo.panda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zoo.panda.entity.User;
import zoo.panda.repository.UserRepository;

/**
 * @author w.rittscher
 * @since 08.10.2015
 */
@RestController
@RequestMapping("rest/user")
public class UserController {

	private final UserRepository userRepository;

	@Autowired
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@RequestMapping("all")
	public Iterable<User> getAllUser() {
		return userRepository.findAll();
	}

}