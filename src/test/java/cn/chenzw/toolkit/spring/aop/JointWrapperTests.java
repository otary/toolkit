package cn.chenzw.toolkit.spring.aop;

import cn.chenzw.toolkit.spring.config.AppConfig;
import cn.chenzw.toolkit.spring.config.WebConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class})
@WebAppConfiguration
public class JointWrapperTests {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;


    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }


    @Test
    public void test() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/hello/say"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("hello!")));
    }
}
