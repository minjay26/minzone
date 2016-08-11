package cn.tendata.minzone.manager.data.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Comment extends AbstractEntityAuditable<Integer>{
	
	@Id
	@GeneratedValue
    private Integer cId;
    
    private String commentContent;
    
  
    
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="uId",unique=false)
    private User commentUser;
    
    private Integer parentCommentId;
    
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="bId")
    private Blog ofBlog;

	public Integer getcId() {
		return cId;
	}

	public void setcId(Integer cId) {
		this.cId = cId;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public User getCommentUser() {
		return commentUser;
	}

	public void setCommentUser(User commentUser) {
		this.commentUser = commentUser;
	}

	public Blog getOfBlog() {
		return ofBlog;
	}

	public void setOfBlog(Blog ofBlog) {
		this.ofBlog = ofBlog;
	}

	public Integer getParentCommentId() {
		return parentCommentId;
	}

	public void setParentCommentId(Integer parentCommentId) {
		this.parentCommentId = parentCommentId;
	}
    
    
   
}
