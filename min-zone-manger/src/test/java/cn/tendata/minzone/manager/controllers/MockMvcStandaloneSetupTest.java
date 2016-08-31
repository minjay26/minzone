package cn.tendata.minzone.manager.controllers;

import org.junit.Before;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
/**
 * 独立测试方式
 * @author Luo Min
 *
 */
public class MockMvcStandaloneSetupTest  {
    
	private MockMvc mockMvc;
	
	@Before
	public void setup(){
		BlogController blogController=new BlogController();
		mockMvc=MockMvcBuilders.standaloneSetup(blogController).build();
	}
}
