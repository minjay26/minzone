package cn.tendata.minzone.manager.service;

import java.util.List;

import cn.tendata.minzone.manager.data.domain.Blog;
import cn.tendata.minzone.manager.data.domain.Comment;

public interface CommentService {

	List<Comment> getAll(Blog blog);

	void comment(Comment comment);

}
