package cn.tendata.minzone.manager.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.tendata.minzone.manager.data.domain.User;
import cn.tendata.minzone.manager.data.dto.UserDto;
import cn.tendata.minzone.manager.data.enums.VType;
import cn.tendata.minzone.manager.service.model.SearchKeywordType;

public interface UserService {
    
	User findById(Integer id);

	void register(User user);

	List<User> getAllByType(VType type);

	UserDto getUserDetail(User user);


}
