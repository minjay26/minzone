package cn.tendata.minzone.manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tendata.minzone.manager.data.domain.Focus;
import cn.tendata.minzone.manager.data.domain.User;
import cn.tendata.minzone.manager.data.dto.UserDto;
import cn.tendata.minzone.manager.data.enums.VType;
import cn.tendata.minzone.manager.repository.BlogRepository;
import cn.tendata.minzone.manager.repository.FocusRepository;
import cn.tendata.minzone.manager.repository.UserRepository;
import cn.tendata.minzone.manager.service.model.SearchKeywordType;

@Service
public class UseServiceImpl implements UserService{
    
	
	private final UserRepository userRepository;
	
	private final BlogRepository blogRepository;
	
	private final FocusRepository focusRepository;
		
	@Autowired
	public UseServiceImpl(
			UserRepository userRepository,
			BlogRepository blogRepository,
			FocusRepository focusRepository) {
		this.userRepository = userRepository;
		this.blogRepository = blogRepository;
		this.focusRepository = focusRepository;
	}

	@Override
	@Transactional(readOnly=true)
	public User findById(Integer id) {
		return userRepository.findOne(id);
	}

	@Override
	public void register(User user) {
		
		this.userRepository.save(user);
	}

	@Override
	@Transactional(readOnly=true)
	public List<User> getAllByType(VType type) {
	      return this.userRepository.findAllByType(type);
	}

	@Override
	@Transactional(readOnly=true)
	public UserDto getUserDetail(User user,User beingFocusUser) {
		long countFan=this.focusRepository.countByBeingFocusUser(beingFocusUser);
		long countBlog=this.blogRepository.countByBlogUser(beingFocusUser);
		long countFocu=this.focusRepository.countByUser(beingFocusUser);
		Focus focus=this.focusRepository.findByUserAndBeingFocusUser(user, beingFocusUser);
		boolean isFocus=false;
		if (focus==null) {			
		}else {
			isFocus=true;
		}
		UserDto userDto=new UserDto();
		userDto.setCountBlogs(countBlog);
		userDto.setCountFans(countFan);
		userDto.setCountFocus(countFocu);
		userDto.setUsername(user.getUsername());
		userDto.setFocus(isFocus);
		return userDto;
	}
	
	@Override
	@Transactional(readOnly=true)
	public UserDto getUserDetail(User user) {
		long countFan=this.focusRepository.countByBeingFocusUser(user);
		long countBlog=this.blogRepository.countByBlogUser(user);
		long countFocu=this.focusRepository.countByUser(user);
		UserDto userDto=new UserDto();
		userDto.setCountBlogs(countBlog);
		userDto.setCountFans(countFan);
		userDto.setCountFocus(countFocu);
		userDto.setUsername(user.getUsername());
		return userDto;
	}
	


}
