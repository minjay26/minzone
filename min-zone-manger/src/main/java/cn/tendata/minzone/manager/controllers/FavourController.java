package cn.tendata.minzone.manager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.tendata.minzone.manager.bind.annotation.CurrentUser;
import cn.tendata.minzone.manager.data.domain.Blog;
import cn.tendata.minzone.manager.data.domain.User;
import cn.tendata.minzone.manager.service.BlogService;
import cn.tendata.minzone.manager.service.FavourService;

@Controller
@RequestMapping("/user_favour")
public class FavourController {
	
	private final BlogService blogService;
	
	@Autowired
	public FavourController(BlogService blogService) {
		this.blogService = blogService;
	}



	@RequestMapping(value="/favour/{bId}",method=RequestMethod.POST)
	public String favour(@PathVariable("bId") Integer bId){
		this.blogService.favour(bId);
		return "";
	}
}
