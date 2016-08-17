package cn.tendata.minzone.manager.data.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class FocusType implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
    private Integer ftId;
    
    private String typeDescribe;
    
    @ManyToOne(optional=false)
    @JoinColumn(name="uId",nullable=false)
    private User user;

	public Integer getFtId() {
		return ftId;
	}

	public void setFtId(Integer ftId) {
		this.ftId = ftId;
	}

	public String getTypeDescribe() {
		return typeDescribe;
	}

	public void setTypeDescribe(String typeDescribe) {
		this.typeDescribe = typeDescribe;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
	
    
}
