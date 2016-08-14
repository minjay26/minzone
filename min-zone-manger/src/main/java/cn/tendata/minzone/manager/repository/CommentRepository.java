package cn.tendata.minzone.manager.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cn.tendata.minzone.manager.data.domain.Blog;
import cn.tendata.minzone.manager.data.domain.Comment;

public interface CommentRepository extends CrudRepository<Comment, Integer>{

	List<Comment> findByOfBlog(Blog blog);

}
