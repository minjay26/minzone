package cn.tendata.minzone.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tendata.minzone.manager.model.entity.User;
import cn.tendata.minzone.manager.repository.UserRepository;
import cn.tendata.minzone.manager.service.UserService;

@Service
public class UseServiceImpl implements UserService{
    
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User findById(Integer id) {
		return userRepository.findOne(id);
	}

}
