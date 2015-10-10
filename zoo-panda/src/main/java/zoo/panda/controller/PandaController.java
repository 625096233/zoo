package zoo.panda.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zoo.panda.entity.Panda;
import zoo.panda.repository.PandaRepository;

import java.util.Arrays;

/**
 * @author w.rittscher
 * @since 08.10.2015
 */
@RestController
public class PandaController {

	private final PandaRepository userRepository;

	@Autowired
	public PandaController(PandaRepository userRepository) {
		this.userRepository = userRepository;
	}

	@RequestMapping("all")
	@HystrixCommand(fallbackMethod = "getAllPandasFallback")
	public Iterable<Panda> getAllPandas() {
		throw new IllegalArgumentException("Test");
	}


	public Iterable<Panda> getAllPandasFallback() {
		return Arrays.asList(Panda.createPanda("Viktor", 15));
	}
}
