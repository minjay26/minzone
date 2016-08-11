package cn.tendata.minzone.manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tendata.minzone.manager.data.domain.User;
import cn.tendata.minzone.manager.data.domain.VType;
import cn.tendata.minzone.manager.repository.UserRepository;

@Service
public class UseServiceImpl implements UserService{
    
	
	private final UserRepository userRepository;
		
	@Autowired
	public UseServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
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

}
