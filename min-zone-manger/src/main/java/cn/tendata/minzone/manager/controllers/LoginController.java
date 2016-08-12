package cn.tendata.minzone.manager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.tendata.minzone.manager.data.domain.User;

@Controller
@RequestMapping("/login")
public class LoginController {
    
	@RequestMapping(value={"","/"})
	public String login(){		
		return "/login";
	}
}
