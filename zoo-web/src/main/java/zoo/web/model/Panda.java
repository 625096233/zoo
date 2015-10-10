package zoo.web.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Waldemar Rittscher
 */
@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Panda {
    private String name;
    private int age;
}
