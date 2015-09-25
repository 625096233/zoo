package zoo.panda.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Waldemar Rittscher
 */
@RestController
@RequestMapping("/")
public class IndexController {
    private final static Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String doGet() {
        LOGGER.info("Panda client got get request");
        return "Hello World from Panda!";
    }

    @RequestMapping(method = RequestMethod.POST)
    public void doPost() {
        LOGGER.info("Panda client got post request");
    }

}
