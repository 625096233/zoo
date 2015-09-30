package zoo.panda.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import zoo.panda.data.entity.User;
import zoo.panda.web.Facade;

/**
 * @author Waldemar Rittscher
 */
@Controller
@RequestMapping("/")
public class IndexController {
    private final static Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    private final Facade facade;

    @Autowired
    public IndexController(Facade facade) {
        this.facade = facade;
    }

    @ModelAttribute("user")
    public Iterable<User> user() {
        return facade.findAllUser();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String doGet() {
        return "index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public void doPost() {
        LOGGER.info("Panda client got post request");
    }

}
