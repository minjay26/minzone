package cn.tendata.minzone.manager.model.entity;

import java.util.Date;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

@Entity
public class Blog {
    
	@Id
	@GeneratedValue
	private Integer bId;
	
	@CreatedBy
	@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="uId",unique=false)
	private User blogUser;
	
	private String content;
	
	@CreatedDate
	private Date creatTime;
	

	private Integer commentCount;
	
	private Integer favour;
	
	@OneToMany(cascade=CascadeType.ALL)
	private Set<Comment> comments;

	public Integer getbId() {
		return bId;
	}

	public void setbId(Integer bId) {
		this.bId = bId;
	}

	public User getBlogUser() {
		return blogUser;
	}

	public void setBlogUser(User blogUser) {
		this.blogUser = blogUser;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Integer getFavour() {
		return favour;
	}

	public void setFavour(Integer favour) {
		this.favour = favour;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	
	
}
