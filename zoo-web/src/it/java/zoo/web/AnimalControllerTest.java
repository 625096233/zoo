package zoo.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import zoo.web.controller.AnimalController;
import zoo.web.model.Panda;
import zoo.web.repository.PandaRepository;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * @author Waldemar Rittscher
 */
@WebAppConfiguration
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Bootstrap.class)
public class AnimalControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PandaRepository pandaRepository;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(new AnimalController(pandaRepository)).build();
    }

    @Test
    public void doesReturnEmptyCollectionOfPandasAsFallbackBecauseLoadBalancerDoesNotFindPandaService() throws Exception {
        Mockito.when(pandaRepository.getAllPandas()).thenReturn(Arrays.asList(
            createPanda("First Panda", 15), createPanda("Second Panda", 10)
        ));
        mockMvc.perform(get("/animal/panda"))
                .andDo(MockMvcResultHandlers.print());
    }

    private Panda createPanda(String name, int age) {
        Panda panda = new Panda();
        panda.setName(name);
        panda.setAge(age);
        return panda;
    }
}
