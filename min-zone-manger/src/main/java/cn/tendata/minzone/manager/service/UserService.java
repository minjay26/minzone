package cn.tendata.minzone.manager.service;

import java.util.List;

import cn.tendata.minzone.manager.data.domain.User;
import cn.tendata.minzone.manager.data.dto.UserDto;
import cn.tendata.minzone.manager.data.enums.VType;

public interface UserService {
    
	User findById(Integer id);

	void register(User user);

	List<User> getAllByType(VType type);

	UserDto getUserDetail(User user);

	
}
