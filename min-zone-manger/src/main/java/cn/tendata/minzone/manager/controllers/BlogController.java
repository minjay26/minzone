package cn.tendata.minzone.manager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tendata.minzone.manager.bind.annotation.CurrentUser;
import cn.tendata.minzone.manager.data.domain.Blog;
import cn.tendata.minzone.manager.data.domain.Comment;
import cn.tendata.minzone.manager.data.domain.FocusType;
import cn.tendata.minzone.manager.data.domain.User;
import cn.tendata.minzone.manager.data.enums.IsShare;
import cn.tendata.minzone.manager.service.BlogService;
import cn.tendata.minzone.manager.service.CommentService;
import cn.tendata.minzone.manager.util.PaginationResult;

@Controller
@RequestMapping("/user_blog")
public class BlogController{
	

		
	private  BlogService blogService;	
	
	private  CommentService commentService;
	
	@Autowired
	public BlogController(BlogService blogService,CommentService commentService) {
		this.blogService = blogService;
		this.commentService = commentService;
	}

	public BlogController() {
		// TODO Auto-generated constructor stub
	}

	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	@ResponseBody
	public String submit(@RequestParam("content") String content,final @CurrentUser  User user) {
        Blog blog=new Blog();
        blog.setBlogUser(user);
        blog.setContent(content);
        blog.setIsShare(IsShare.NO);
		this.blogService.addBlog(blog);
		return "success";
	}


   @RequestMapping(value="/favour/{bId}",method=RequestMethod.POST)
   @ResponseBody
   public String favour(@PathVariable("bId") Integer bId){
	   this.blogService.favour(bId);
	   return "";
   }

  
   @RequestMapping(value="/getAllBlog/{page}",method=RequestMethod.GET)
   @ResponseBody
   public PaginationResult<Blog> getAll(
		   @PathVariable("page") Integer page,
		   final @CurrentUser User user,
		   PaginationResult<Blog> result){
	   List<Blog> personBLogs=this.blogService.getAll(user,(page-1)*5,5);
		result.fillData(personBLogs);
		return result;
   }
    
   @RequestMapping(value="/getAllBlog/{type}/{page}",method=RequestMethod.GET)
   @ResponseBody
	public PaginationResult<Blog> getAllByType(
			@PathVariable("type") FocusType focusType,
			@PathVariable("page") Integer page,
			final @CurrentUser User user,
			PaginationResult<Blog> result){
		List<Blog> blogs=this.blogService.getAllByType(focusType.getFtId(),(page-1)*5,5);
		result.fillData(blogs);
	   return result;
	}
   
   @RequestMapping(value="/share/{bId}",method=RequestMethod.GET)
   public String share(@PathVariable("bId") Blog blog,ModelMap map){
	   List<Comment> list=this.commentService.getAll(blog);
	   map.addAttribute("bId", blog.getbId());
	   map.addAttribute("lists", list);
	   return "/blog/share";
   }
   
   @RequestMapping(value="/share/{bId}",method=RequestMethod.POST)
   @ResponseBody
   public String share(@PathVariable("bId") Blog shareBlog,@RequestParam("content") String content,@CurrentUser User user){
         Blog blog=new Blog();
         blog.setShareBlog(shareBlog);
         blog.setBlogUser(user);
         blog.setContent(content);
         blog.setIsShare(IsShare.YES);
        this.blogService.addBlog(blog);
        this.blogService.addShare(shareBlog);
	   return "success";
   }

}
