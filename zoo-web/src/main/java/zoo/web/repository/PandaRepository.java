package zoo.web.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import zoo.web.model.Panda;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

/**
 * @author Waldemar Rittscher
 */
@Repository
public class PandaRepository {

    private final RestTemplate restTemplate;

    private final LoadBalancerClient loadBalancerClient;

    @Autowired
    public PandaRepository(LoadBalancerClient loadBalancerClient, RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.loadBalancerClient = loadBalancerClient;
    }

    public List<Panda> getAllPandas() {
        URI pandaInstanceURI = loadBalancerClient.choose("panda").getUri();
        Panda[] pandaArray = restTemplate.getForObject(pandaInstanceURI, Panda[].class);
        return Arrays.asList(pandaArray);
    }

}
