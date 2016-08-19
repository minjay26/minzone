package cn.tendata.minzone.manager.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import cn.tendata.minzone.manager.data.domain.Blog;
import cn.tendata.minzone.manager.data.domain.FocusType;
import cn.tendata.minzone.manager.data.domain.User;


public interface BlogRepository extends JpaRepository<Blog, Integer>{
    
	@Query(value="SELECT DISTINCT b.* from blog b inner JOIN (select bf_uid from focus f WHERE f.u_id=:uId) a on b.u_id=a.bf_uid or b.u_id=:uId order by created_date desc "
			+ "limit :beginSite,:size",nativeQuery=true)
	List<Blog> findAllByBlogUser(@Param("uId") Integer uId,@Param("beginSite") Integer beginSite,@Param("size") Integer size);
    
	@Transactional
	@Modifying
	@Query(value="update Blog b set b.favour=b.favour+1 where b.bId=?1")
	void favour(@Param("bId") Integer bId);

	@Transactional
	@Modifying
	@Query(value="update Blog b set b.commentCount=b.commentCount+1 where b.bId=?1")
	void addCommentCount(Integer bId);
     
	@Query(value="select b from Blog b,Focus f where f.type=:type and f.user=:user and b.blogUser=f.beingFocusUser")
	Page<Blog> findAllByType(@Param("user")User user,@Param("type") FocusType focusType, Pageable pageable);
	
	@Query(value="select b.* from blog b INNER JOIN (select f.bf_uid from focus f where f.ft_id=:ftId) bf on b.u_id=bf.bf_uid limit :beginSite,:size",nativeQuery=true)
	List<Blog> findAllByType(@Param("ftId")Integer ftId,@Param("beginSite") Integer beginSite,@Param("size") Integer size);
     
	
	@Transactional
	@Modifying
	@Query(value="update Blog b set b.countShare=b.countShare+1 where b.bId=?1")
	void addShareCount(Integer getbId);

	long countByBlogUser(User user);

}
