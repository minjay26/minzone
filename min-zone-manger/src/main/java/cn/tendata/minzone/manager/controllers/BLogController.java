package cn.tendata.minzone.manager.controllers;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user_blog")
public class BLogController {
    
	
	@RequestMapping(value="/submit",method=RequestMethod.POST)
	//@ResponseBody
	public String submit(@RequestParam String content){
		System.out.println(content);
		return "/success";
	}
}
