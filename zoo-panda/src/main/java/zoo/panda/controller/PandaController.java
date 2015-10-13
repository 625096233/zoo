package zoo.panda.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zoo.panda.entity.Panda;
import zoo.panda.repository.PandaRepository;

import java.util.Collections;
import java.util.Random;

/**
 * @author w.rittscher
 * @since 08.10.2015
 */
@RestController
public class PandaController {

	private final PandaRepository pandaRepository;

	private final Random random = new Random();

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
		int randomInt = random.nextInt(10) + 1;
		if (randomInt % 3 == 0) {
			throw new RuntimeException("Hystrix trigger");
		}
		return pandaRepository.findAll();
	}

	public Panda getPandaFallback(String name) {
		return Panda.createEmpty();
	}

	public Iterable<Panda> getAllPandasFallback() {
		return Collections.emptyList();
	}
}
