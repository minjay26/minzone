package cn.tendata.minzone.manager.data.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Role {
    
	@Id
	@GeneratedValue
	private int id;
	
	private String roleDescribe;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleDescribe() {
		return roleDescribe;
	}

	public void setRoleDescribe(String roleDescribe) {
		this.roleDescribe = roleDescribe;
	}

	public Role() {

	}

	public Role(int id, String roleDescribe) {
		this.id = id;
		this.roleDescribe = roleDescribe;
	}
	
	
}
