package cn.tendata.minzone.manager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.tendata.minzone.manager.bind.annotation.CurrentUser;
import cn.tendata.minzone.manager.data.domain.Blog;
import cn.tendata.minzone.manager.data.domain.User;
import cn.tendata.minzone.manager.service.BlogService;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	private BlogService blogService;
	
	@Autowired
	public HomeController(BlogService blogService) {
		this.blogService = blogService;
	}



	@RequestMapping(method=RequestMethod.GET)
	public String home(ModelMap map,final @CurrentUser User user){
		List<Blog> personBLogs=this.blogService.getAll(user);
		map.addAttribute("user", user);
		map.addAttribute("personBLogs", personBLogs);
		return "/home";
	}
}
