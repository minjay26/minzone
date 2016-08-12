package cn.tendata.minzone.manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tendata.minzone.manager.data.domain.Blog;
import cn.tendata.minzone.manager.data.domain.User;
import cn.tendata.minzone.manager.repository.BlogRepository;

@Service
public class BlogServiceImpl implements BlogService{
  
	@Autowired
	private BlogRepository blogRepository;
	
//	@Override
//	public void upload(UploadImage image) {
//		this.blogRepository.save(image);		
//	}

	@Override
	@Transactional
	public void addBlog(Blog blog) {
		
		this.blogRepository.save(blog);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Blog> getAll(User user,Integer beginSite,Integer size) {
		return this.blogRepository.findAllByBlogUser(user.getuId(),beginSite,size);
		
	}
	
	

}
