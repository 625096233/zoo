package zoo.panda.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import zoo.panda.data.entity.User;
import zoo.panda.web.Facade;

/**
 * @author w.rittscher
 * @since 02.10.2015
 */
@Controller
@RequestMapping("/admin/user")
public class UserController {

	private final Facade facade;

	@Autowired
	public UserController(Facade facade) {
		this.facade = facade;
	}

	@ModelAttribute("user")
	public Iterable<User> user() {
		return facade.findAllUser();
	}

	@RequestMapping
	public String loadPage() {
		return "/admin/user";
	}

}
