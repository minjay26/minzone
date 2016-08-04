package cn.tendata.minzone.manager.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import cn.tendata.minzone.manager.data.domain.User;
import cn.tendata.minzone.manager.repository.UserRepository;

public class CustomUserService implements UserDetailsService{
  
	@Autowired
	private UserRepository userRepository;
	
    
	
	
	public CustomUserService() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userRepository.findByUsername(username);

		
		if (user==null) {
			throw new UsernameNotFoundException("该用户不存在");
		}
	
		return user;
		
	}

}
