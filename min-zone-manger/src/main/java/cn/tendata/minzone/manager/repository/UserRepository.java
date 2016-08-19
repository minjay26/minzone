package cn.tendata.minzone.manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cn.tendata.minzone.manager.data.domain.User;
import cn.tendata.minzone.manager.data.enums.VType;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUsername(String username);
    
	@Query("select u.uId,u.username from User u where u.type=?1")
	List<User> findAllByType(VType type);

}
