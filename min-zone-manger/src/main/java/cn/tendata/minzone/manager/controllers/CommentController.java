package cn.tendata.minzone.manager.controllers;

import java.util.List;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tendata.minzone.manager.bind.annotation.CurrentUser;
import cn.tendata.minzone.manager.data.domain.Blog;
import cn.tendata.minzone.manager.data.domain.Comment;
import cn.tendata.minzone.manager.data.domain.User;
import cn.tendata.minzone.manager.service.BlogService;
import cn.tendata.minzone.manager.service.CommentService;

@Controller
@RequestMapping("/user_comment")
public class CommentController {
	
	private final CommentService commentService;
	
	private final BlogService blogService;
	
	@Autowired
	public CommentController(CommentService commentService,BlogService blogService) {
		this.commentService = commentService;
		this.blogService = blogService;
	}



	@RequestMapping(value="/getComments/{bId}",method=RequestMethod.GET)
	@ResponseBody
	public List<Comment> getComment(@PathVariable("bId") Blog blog){
		List<Comment> comments=this.commentService.getAll(blog);		
		return comments;
	}
	
	@RequestMapping(value="/comment/{bId}",method=RequestMethod.POST)
	@ResponseBody
	public void comment(
			@RequestParam String content,
			@PathVariable("bId") Blog blog,
			@CurrentUser User user) {
		Comment comment=new Comment();
		comment.setCommentContent(content);
		comment.setOfBlog(blog);
		comment.setCommentUser(user);
		this.commentService.comment(comment);
		this.blogService.addCommentCount(blog.getbId());
	}
}
