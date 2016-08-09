package cn.tendata.minzone.manager.data.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Focus extends AbstractEntityAuditable<Integer>{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer fId;
	
	@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="uId",unique=false)
	private User user;
	
	@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="ftId",unique=false,nullable=true)
	private FocusType type;
	
	@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="bfUid",unique=false)
	private User beingFocusUser;

	public Integer getfId() {
		return fId;
	}

	public void setfId(Integer fId) {
		this.fId = fId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public FocusType getType() {
		return type;
	}

	public void setType(FocusType type) {
		this.type = type;
	}

	public User getBeingFocusUser() {
		return beingFocusUser;
	}

	public void setBeingFocusUser(User beingFocusUser) {
		this.beingFocusUser = beingFocusUser;
	}
	
	
	
	
}
