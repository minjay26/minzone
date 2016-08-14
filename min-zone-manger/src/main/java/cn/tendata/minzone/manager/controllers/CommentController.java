package cn.tendata.minzone.manager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tendata.minzone.manager.data.domain.Blog;
import cn.tendata.minzone.manager.data.domain.Comment;
import cn.tendata.minzone.manager.service.CommentService;

@Controller
@RequestMapping("/user_comment")
public class CommentController {
	
	private final CommentService commentService;
	
	@Autowired
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}



	@RequestMapping(value="/getComments/{bId}",method=RequestMethod.GET)
	@ResponseBody
	public List<Comment> getComment(@PathVariable("bId") Blog blog){
		List<Comment> comments=this.commentService.getAll(blog);		
		return comments;
	}
}
