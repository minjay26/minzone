package cn.tendata.minzone.manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tendata.minzone.manager.data.domain.User;
import cn.tendata.minzone.manager.data.dto.UserDto;
import cn.tendata.minzone.manager.data.enums.VType;
import cn.tendata.minzone.manager.repository.BlogRepository;
import cn.tendata.minzone.manager.repository.FocusRepository;
import cn.tendata.minzone.manager.repository.UserRepository;

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
	public User findById(Integer id) {
		return userRepository.findOne(id);
	}

	@Override
	public void register(User user) {
		
		this.userRepository.save(user);
	}

	@Override
	public List<User> getAllByType(VType type) {
	      return this.userRepository.findAllByType(type);
	}

	@Override
	public UserDto getUserDetail(User user) {
		long countFan=this.focusRepository.countByBeingFocusUser(user);
		long countBlog=this.blogRepository.countByBlogUser(user);
		long countFocu=this.focusRepository.countByUser(user);
		UserDto userDto=new UserDto();
		userDto.setCountBlogs(countBlog);
		userDto.setCountFans(countFan);
		userDto.setCountFocus(countFocu);
		return userDto;
	}

}
