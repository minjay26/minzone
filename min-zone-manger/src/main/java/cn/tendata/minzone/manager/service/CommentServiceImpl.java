package cn.tendata.minzone.manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tendata.minzone.manager.data.domain.Blog;
import cn.tendata.minzone.manager.data.domain.Comment;
import cn.tendata.minzone.manager.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
    private CommentRepository commentRepository;
    
	@Override
	public List<Comment> getAll(Blog blog) {
		System.out.println(commentRepository);
	  List<Comment> comments=this.commentRepository.findByOfBlog(blog);
		return comments;
	}

}
