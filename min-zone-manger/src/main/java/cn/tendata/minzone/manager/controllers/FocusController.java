package cn.tendata.minzone.manager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.tendata.minzone.manager.bind.annotation.CurrentUser;
import cn.tendata.minzone.manager.data.domain.Blog;
import cn.tendata.minzone.manager.data.domain.FocusType;
import cn.tendata.minzone.manager.data.domain.User;
import cn.tendata.minzone.manager.service.BlogService;

@Controller
@RequestMapping("/user_focus")
public class FocusController {
   
    private final BlogService blogService;	
	
	@Autowired
	public FocusController(BlogService blogService) {
		this.blogService = blogService;
	}
	
	@RequestMapping(value="/{ftId}",method=RequestMethod.GET)
	public String getAll(@PathVariable("ftId") FocusType focusType,@CurrentUser User user,ModelMap map){
		 Pageable pageable=new PageRequest(1, 5);
		 Page<Blog> blogs=this.blogService.getAllByType(user,focusType,pageable);
		 int sumPage=blogs.getTotalPages();
		 map.addAttribute("sumPage", sumPage);
		 map.addAttribute("user", user);
		 return "/focus/eachTypeFocus";
	}
}
