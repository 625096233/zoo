package zoo.panda.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zoo.panda.data.entity.User;
import zoo.panda.data.repository.UserRepository;

/**
 * @author Waldemar Rittscher
 */
@Component
public class Facade {

    private UserRepository userRepository;

    @Autowired
    public Facade(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<User> findAllUser() {
        return userRepository.findAll();
    }

}
