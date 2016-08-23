package cn.tendata.minzone.manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tendata.minzone.manager.data.domain.Blog;
import cn.tendata.minzone.manager.data.domain.FocusType;
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

	@Override
	public void favour(Integer bId) {
		this.blogRepository.favour(bId);
		
	}

	@Override
	public Integer count(User user, int i, int maxValue) {
		return (int) this.blogRepository.count();
	}

	@Override
	public void addCommentCount(Integer bId) {
		this.blogRepository.addCommentCount(bId);
		
	}

	@Override
	public Page<Blog> getAllByType(User user, FocusType focusType,Pageable pageable) {
		
		return this.blogRepository.findAllByType(user,focusType,pageable);
	}

	@Override
	public List<Blog> getAllByType(Integer ftId,Integer beginSite,Integer size) {
		return this.blogRepository.findAllByType(ftId,beginSite,size);
	}

	@Override
	public void addShare(Blog shareBlog) {
		this.blogRepository.addShareCount(shareBlog.getbId());
		
	}
	
	

}
