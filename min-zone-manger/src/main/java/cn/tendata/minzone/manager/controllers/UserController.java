package cn.tendata.minzone.manager.controllers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.tendata.minzone.manager.bind.annotation.CurrentUser;
import cn.tendata.minzone.manager.data.domain.User;
import cn.tendata.minzone.manager.data.enums.VType;
import cn.tendata.minzone.manager.service.BlogService;
import cn.tendata.minzone.manager.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private final UserService userService;
	
	private  final BlogService blogService;
	
	private static final int BUFFER_SIZE = 1 * 1024;

	
	@Autowired
	public UserController(UserService userService,BlogService blogService) {
		this.userService = userService;
		this.blogService=blogService;
	}

	@RequestMapping(value="/register",method=RequestMethod.GET)
	public String register(){
            return "/user/register";
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	@ResponseBody
	public String  register(User user){
		user.setType(VType.COMMON);
		this.userService.register(user);
		 return "success";
	}
	
	@RequestMapping("/register_success")
	public String registerSuccess(){
		return "/user/register_success";
	}
	
	@RequestMapping(value="/{type}/getAll",method=RequestMethod.GET)
	//@ResponseBody
	public String getAllStarUser(@PathVariable("type") VType type,ModelMap map){
		List<User> users=this.userService.getAllByType(type);
		map.addAttribute("users", users);
		return "/user/eachTypeUsers";
		//return users;
	}
	
	@RequestMapping("/visit_user/{uId}")
	public String visitUser(@PathVariable("uId") User user,ModelMap map){
		//List<Blog> blogs=this.blogService.getAll(user);
		map.addAttribute("user", user);	
		//map.addAttribute("blogs", blogs);
		return "/user/visit_user";
	}
	
	@RequestMapping(value="/uploadHead",method=RequestMethod.POST)
	@ResponseBody
	public String upload(MultipartFile file,HttpServletRequest request,HttpSession session,HttpServletResponse response,@CurrentUser User user) throws IOException{
		//response.addHeader("X-Frame-Options", "SAMEORIGIN");
		return "success";
}
}
