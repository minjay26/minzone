package cn.tendata.minzone.manager.data.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class UploadImage extends AbstractEntityAuditable<Integer>{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer imageId;
	
	private String filePath;
	

	@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="uId",unique=false)
	private User upLoadUser;

	public Integer getImageId() {
		return imageId;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public User getUpLoadUser() {
		return upLoadUser;
	}

	public void setUpLoadUser(User upLoadUser) {
		this.upLoadUser = upLoadUser;
	}
	
	
}
