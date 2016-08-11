package cn.tendata.minzone.manager.data.domain;

import java.io.Serializable;

import javax.persistence.Transient;

import org.springframework.data.domain.Persistable;

import com.fasterxml.jackson.annotation.JsonView;


import cn.tendata.minzone.manager.data.jackson.DataView;

public abstract class AbstractEntity<ID extends Serializable> implements Persistable<ID>,Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonView(DataView.Basic.class)
	protected ID id;

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}
	
	@Transient
	public boolean isNew() {
		return null == getId();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return String.format("Entity of type %s with id: %s", this.getClass().getName(), getId());
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {

		if (null == obj) {
			return false;
		}

		if (this == obj) {
			return true;
		}

		if (!getClass().equals(obj.getClass())) {
			return false;
		}

		AbstractEntity<?> that = (AbstractEntity<?>) obj;

		return null == this.getId() ? false : this.getId().equals(that.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {

		int hashCode = 17;

		hashCode += null == getId() ? 0 : getId().hashCode() * 31;

		return hashCode;
	}
	
	

}
