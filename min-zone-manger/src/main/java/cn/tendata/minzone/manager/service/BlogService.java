package cn.tendata.minzone.manager.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.tendata.minzone.manager.data.domain.Blog;
import cn.tendata.minzone.manager.data.domain.User;


public interface BlogService {
//    void upload(UploadImage image);

	void addBlog(Blog blog);
	
	List<Blog> getAll(User user,Integer beginSite,Integer size);
}
