package zoo.panda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author w.rittscher
 * @since 25.09.2015
 */
@EntityScan
@Configuration
@ComponentScan
@EnableHystrix
@EnableJpaRepositories
@EnableDiscoveryClient
@EnableAutoConfiguration
public class PandaTamer {

	public static void main(String[] args) {
		SpringApplication.run(PandaTamer.class, args);
	}

}
