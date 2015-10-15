package zoo.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.amqp.EnableTurbineAmqp;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Waldemar Rittscher
 */
@ComponentScan
@Configuration
@EnableTurbineAmqp
@EnableDiscoveryClient
@EnableAutoConfiguration
public class Bootstrap {

    public static void main(String[] args) {
        SpringApplication.run(Bootstrap.class, args);
    }

}
