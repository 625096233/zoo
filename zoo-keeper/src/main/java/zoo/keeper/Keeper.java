package zoo.keeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author w.rittscher
 * @since 25.09.2015
 */
@Configuration
@ComponentScan
@EnableEurekaServer
@EnableDiscoveryClient
@EnableAutoConfiguration
public class Keeper {

	public static void main(String[] args) {
		SpringApplication.run(Keeper.class, args);
	}

}
