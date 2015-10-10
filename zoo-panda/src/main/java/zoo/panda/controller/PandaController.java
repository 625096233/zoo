package zoo.panda.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zoo.panda.entity.Panda;
import zoo.panda.repository.PandaRepository;

import java.util.Collections;

/**
 * @author w.rittscher
 * @since 08.10.2015
 */
@RestController
public class PandaController {

	private final PandaRepository pandaRepository;

	@Autowired
	public PandaController(PandaRepository pandaRepository) {
		this.pandaRepository = pandaRepository;
	}

	@RequestMapping("panda")
	@HystrixCommand(fallbackMethod = "getPandaFallback")
	public Panda getPanda(@RequestParam String name) {
		return pandaRepository.findPandaByName(name);
	}

	@RequestMapping("all")
	@HystrixCommand(fallbackMethod = "getAllPandasFallback")
	public Iterable<Panda> getAllPandas() {
		return pandaRepository.findAll();
	}

	public Panda getPandaFallback(String name) {
		return Panda.createEmpty();
	}

	public Iterable<Panda> getAllPandasFallback() {
		return Collections.emptyList();
	}
}
