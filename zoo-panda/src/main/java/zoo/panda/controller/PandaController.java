package zoo.panda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zoo.panda.entity.Panda;
import zoo.panda.repository.PandaRepository;

/**
 * @author w.rittscher
 * @since 08.10.2015
 */
@RestController
@RequestMapping("rest/panda")
public class PandaController {

	private final PandaRepository userRepository;

	@Autowired
	public PandaController(PandaRepository userRepository) {
		this.userRepository = userRepository;
	}

	@RequestMapping("all")
	public Iterable<Panda> getAllPandas() {
		return userRepository.findAll();
	}

}
