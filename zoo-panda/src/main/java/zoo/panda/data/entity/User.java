package zoo.panda.data.entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * @author Waldemar Rittscher
 */
public class User extends AbstractPersistable<Long> {

    private String name;

    protected User() {}

    public static User createUser(String name) {
        User user = new User();
        user.name = name;
        return user;
    }

    public String getName() {
        return name;
    }
}
