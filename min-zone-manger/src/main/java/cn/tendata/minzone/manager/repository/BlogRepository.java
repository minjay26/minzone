package cn.tendata.minzone.manager.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.tendata.minzone.manager.data.domain.Blog;


public interface BlogRepository extends JpaRepository<Blog, Integer>{
    
	@Query(value="SELECT DISTINCT b.* from blog b INNER JOIN (select bf_uid from focus f WHERE f.u_id=:uId) a on b.u_id=a.bf_uid or b.u_id=:uId order by created_date desc "
			+ "limit :beginSite,:size",nativeQuery=true)
	List<Blog> findAllByBlogUser(@Param("uId") Integer uId,@Param("beginSite") Integer beginSite,@Param("size") Integer size);

}
