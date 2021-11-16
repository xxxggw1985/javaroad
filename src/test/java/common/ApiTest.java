package common;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.env.Environment;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ActiveProfiles("pre")
//@RunWith(SpringRunner.class)
public abstract class ApiTest {
    protected String baseUrl;

    @Autowired
    protected Environment environment;

    @Resource
    protected TestRestTemplate restTemplate;

    protected MockMvc mockMvc;
    @Autowired
    protected WebApplicationContext wac;



    @BeforeEach
    public void setUp() throws Exception {
        String port = environment.getProperty("local.server.port");
        String contextPath = environment.getProperty("server.servlet.context-path","");
        baseUrl = "http://localhost:" + port + contextPath;
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();  //初始化MockMvc对象
    }

}
