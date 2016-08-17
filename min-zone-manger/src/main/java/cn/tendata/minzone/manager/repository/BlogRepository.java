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

}
