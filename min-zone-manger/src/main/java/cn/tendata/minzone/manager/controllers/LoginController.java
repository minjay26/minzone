package cn.tendata.minzone.manager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.tendata.minzone.manager.data.domain.User;

@Controller
@RequestMapping("/login")
public class LoginController {
    
	@RequestMapping(value={"","/"},method=RequestMethod.GET)
	public String login(){		
		return "/login";
	}
	
//	@RequestMapping(method=RequestMethod.POST)
//	public String login(User user){
//		
//		return "/home";
//	}
}
