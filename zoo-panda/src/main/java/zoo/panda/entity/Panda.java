package zoo.panda.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Waldemar Rittscher
 */
@Entity
@Getter
@Table(name = "panda")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Panda extends AbstractPersistable<Long> {

    private String name;

	private int age;

    public static Panda createPanda(String name, int age) {
        Panda panda = new Panda();
        panda.name = name;
        panda.age = age;
        return panda;
    }

}
