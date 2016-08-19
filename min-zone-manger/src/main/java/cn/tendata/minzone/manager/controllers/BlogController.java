package cn.tendata.minzone.manager.controllers;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;

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
	

		
	private final BlogService blogService;	
	
	private final CommentService commentService;
	
	@Autowired
	public BlogController(BlogService blogService,CommentService commentService) {
		this.blogService = blogService;
		this.commentService = commentService;
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
