package cn.tendata.minzone.manager.service;

import java.util.List;

import cn.tendata.minzone.manager.data.domain.FocusType;
import cn.tendata.minzone.manager.data.domain.User;

public interface FocusTypeService {
    
	List<FocusType> getAll(User user);

}
