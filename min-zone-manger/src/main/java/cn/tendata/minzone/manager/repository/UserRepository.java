package cn.tendata.minzone.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.tendata.minzone.manager.data.domain.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUsername(String username);

}
