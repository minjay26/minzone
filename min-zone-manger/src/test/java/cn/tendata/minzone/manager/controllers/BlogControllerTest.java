package cn.tendata.minzone.manager.controllers;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class BlogControllerTest extends MockMvcTestSupport{
     
	@Test
	public void testView() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/user_blog/share/1"))
				.andExpect(MockMvcResultMatchers.view().name("/blog/share"))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}
}
