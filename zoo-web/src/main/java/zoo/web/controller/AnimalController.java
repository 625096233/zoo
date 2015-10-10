package zoo.web.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zoo.web.model.Panda;
import zoo.web.repository.PandaRepository;

import java.util.Collections;
import java.util.List;

/**
 * @author Waldemar Rittscher
 */
@RestController
@RequestMapping("animal")
public class AnimalController {

    private final PandaRepository pandaRepository;

    @Autowired
    public AnimalController(PandaRepository pandaRepository) {
        this.pandaRepository = pandaRepository;
    }

    @RequestMapping("panda")
    @HystrixCommand(fallbackMethod = "getAllPandasFallback")
    public List<Panda> getAllPandas() {
        return pandaRepository.getAllPandas();
    }

    public List<Panda> getAllPandasFallback() {
        return Collections.emptyList();
    }


}
