package cn.tendata.minzone.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.tendata.minzone.manager.data.domain.Focus;
import cn.tendata.minzone.manager.data.domain.User;

public interface FocusRepository extends JpaRepository<Focus, Integer>{

	Long countByBeingFocusUser(User user);

	long countByUser(User user);
	
	Focus findByUserAndBeingFocusUser(User user,User beingFocusUser);

}
