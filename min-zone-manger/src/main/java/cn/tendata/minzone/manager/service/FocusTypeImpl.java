package cn.tendata.minzone.manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tendata.minzone.manager.data.domain.FocusType;
import cn.tendata.minzone.manager.data.domain.User;
import cn.tendata.minzone.manager.repository.FocusTypeRepository;

@Service
public class FocusTypeImpl implements FocusTypeService{
    
	@Autowired
	private FocusTypeRepository focusTypeRepository;
	
	
	@Override
	public List<FocusType> getAll(User user) {	
		return this.focusTypeRepository.findByUser(user);
	}

}
