package cn.tendata.minzone.manager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.tendata.minzone.manager.bind.annotation.CurrentUser;
import cn.tendata.minzone.manager.model.entity.User;

@Controller
@RequestMapping("/home")
public class HomeController {
    
	@RequestMapping(method=RequestMethod.GET)
	public String home(ModelMap map,@CurrentUser User user){
		map.addAttribute("user", user);
		return "/home";
	}
}
