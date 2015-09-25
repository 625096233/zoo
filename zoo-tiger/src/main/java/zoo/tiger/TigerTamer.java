package zoo.tiger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author w.rittscher
 * @since 25.09.2015
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class TigerTamer {

	public static void main(String[] args) {
		SpringApplication.run(TigerTamer.class, args);
	}

}
