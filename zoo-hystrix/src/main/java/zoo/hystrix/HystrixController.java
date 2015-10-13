package zoo.hystrix;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Waldemar Rittscher
 */
@Controller
@RequestMapping("/")
public class HystrixController {

    @RequestMapping
    public String forwardToHystrix() {
        return "forward:/hystrix";
    }

}
