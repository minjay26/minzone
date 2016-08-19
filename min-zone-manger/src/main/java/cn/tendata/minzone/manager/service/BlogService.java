package cn.tendata.minzone.manager.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.tendata.minzone.manager.data.domain.Blog;
import cn.tendata.minzone.manager.data.domain.FocusType;
import cn.tendata.minzone.manager.data.domain.User;


public interface BlogService {
//    void upload(UploadImage image);

	void addBlog(Blog blog);
	
	List<Blog> getAll(User user,Integer beginSite,Integer size);

	void favour(Integer bId);

	Integer count(User user, int i, int maxValue);

	void addCommentCount(Integer getbId);

	Page<Blog> getAllByType(User user,FocusType focusType, Pageable pageable);
	
	List<Blog> getAllByType(Integer ftId,Integer beginSite,Integer size);

	void addShare(Blog shareBlog);
}
