package cn.tendata.minzone.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tendata.minzone.manager.data.domain.User;
import cn.tendata.minzone.manager.repository.UserRepository;

@Service
public class UseServiceImpl implements UserService{
    
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User findById(Integer id) {
		return userRepository.findOne(id);
	}

}
