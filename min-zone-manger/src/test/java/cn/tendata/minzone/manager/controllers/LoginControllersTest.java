package cn.tendata.minzone.manager.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;




public class LoginControllersTest{
    
	private LoginController loginController;
	
	@Before
	public void setup(){
		loginController=new LoginController();
	}
	@Test
	public void homeShouldRenderHomeView(){
		//MockHttpServletRequest request=new MockHttpServletRequest();
		String view=loginController.login();
		Assert.assertEquals(view, "/login");
	}
}
