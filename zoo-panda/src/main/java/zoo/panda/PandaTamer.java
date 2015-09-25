package zoo.panda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author w.rittscher
 * @since 25.09.2015
 */
@Configuration
@ComponentScan
@EnableDiscoveryClient
@EnableAutoConfiguration
public class PandaTamer {

	public static void main(String[] args) {
		SpringApplication.run(PandaTamer.class, args);
	}

}
