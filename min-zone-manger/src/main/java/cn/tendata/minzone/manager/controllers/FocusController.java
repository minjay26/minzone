package cn.tendata.minzone.manager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.tendata.minzone.manager.bind.annotation.CurrentUser;
import cn.tendata.minzone.manager.data.domain.User;

@Controller
@RequestMapping("/user_focus")
public class FocusController {
   
	@RequestMapping(value="/{ftId}",method=RequestMethod.GET)
	public String getAll(@PathVariable("ftId") Integer ftId,@CurrentUser User user,ModelMap map){
		map.addAttribute("user", user);
		return "/focus/eachTypeFocus";
	}
}
