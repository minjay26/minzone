package cn.tendata.minzone.manager.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.tendata.minzone.manager.bind.annotation.CurrentUser;
import cn.tendata.minzone.manager.data.domain.FocusType;
import cn.tendata.minzone.manager.data.domain.User;
import cn.tendata.minzone.manager.data.dto.UserDto;
import cn.tendata.minzone.manager.service.BlogService;
import cn.tendata.minzone.manager.service.FocusTypeService;
import cn.tendata.minzone.manager.service.UserService;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	private final BlogService blogService;
	
	private final FocusTypeService focusTypeService;
	
	private final UserService userService;


	@Autowired
	public HomeController(
			BlogService blogService,
			FocusTypeService focusTypeService,
			UserService userService) {
		this.blogService = blogService;
		this.focusTypeService = focusTypeService;
		this.userService = userService;
	}

	






	@RequestMapping(method=RequestMethod.GET)
	public String home(ModelMap map,final @CurrentUser User user,HttpSession session){
		int sumPage=this.blogService.getAll(user, 0, Integer.MAX_VALUE).size()/8+1;
		List<FocusType> items=this.focusTypeService.getAll(user);
		UserDto userDto=this.userService.getUserDetail(user);
		map.addAttribute("sumPage", sumPage);
		map.addAttribute("user", user);
		session.setAttribute("items", items);
		session.setAttribute("userDto", userDto);	
		return "/home";
		
	}
	
//	@RequestMapping(value="/{page}",method=RequestMethod.GET)
//	public String home(@PathVariable("page") Integer page,ModelMap map,final @CurrentUser User user){
//		List<Blog> personBLogs=this.blogService.getAll(user,(page-1)*5,5);
//		int sumPage=this.blogService.getAll(user, 0, Integer.MAX_VALUE).size()/8+1;
//		map.addAttribute("user", user);
//		map.addAttribute("personBLogs", personBLogs);
//		map.addAttribute("sumPage", sumPage);
//		return "/home";
//	}
}
