package cn.tendata.minzone.manager.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cn.tendata.minzone.manager.data.domain.FocusType;
import cn.tendata.minzone.manager.data.domain.User;

public interface FocusTypeRepository extends CrudRepository<FocusType, Integer>{

	List<FocusType> findByUser(User user);

}
