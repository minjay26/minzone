package cn.tendata.minzone.manager.data.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import cn.tendata.minzone.manager.data.enums.FStatus;

@Entity
public class Favour {
  
	@Id
	@GeneratedValue
	private Integer id;
	
	@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="uId",unique=false)
	private User favourUser;
	
	@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="bId",unique=false)
	private Blog favourBlog;
	
	private FStatus status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getFavourUser() {
		return favourUser;
	}

	public void setFavourUser(User favourUser) {
		this.favourUser = favourUser;
	}

	public Blog getFavourBlog() {
		return favourBlog;
	}

	public void setFavourBlog(Blog favourBlog) {
		this.favourBlog = favourBlog;
	}

	public FStatus getStatus() {
		return status;
	}

	public void setStatus(FStatus status) {
		this.status = status;
	}
	
	
	
}
