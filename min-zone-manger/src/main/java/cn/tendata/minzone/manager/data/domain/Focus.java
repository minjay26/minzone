package cn.tendata.minzone.manager.data.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Focus extends AbstractEntityAuditable<Integer>{
    
	@Id
	@GeneratedValue
	private Integer fId;
	
	@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="uId",unique=false)
	private User user;
	
	@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="ftId",unique=false)
	private FocusType type;
	
	
}
