package cn.tendata.minzone.manager.controllers;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import cn.tendata.minzone.manager.config.TestConfig;

/**
 * 测试环境的搭建
 * @author Luo Min
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {TestConfig.class})
@TestExecutionListeners({
    DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class})
@ActiveProfiles("test")
public abstract class MockMvcTestSupport {
    
	@Autowired
	protected WebApplicationContext context;
	
	protected MockMvc mockMvc;
	
	@Before
	public void setup(){
		this.mockMvc=MockMvcBuilders.webAppContextSetup(context).build();
	}
	
}
