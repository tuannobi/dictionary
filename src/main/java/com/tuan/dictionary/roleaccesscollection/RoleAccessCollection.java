package com.tuan.dictionary.roleaccesscollection;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tuan.dictionary.collection.Collection;
import com.tuan.dictionary.user.User;
@Entity
@Table(name = "role_access_collection")
public class RoleAccessCollection implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	private User user;
	
	@Id
	@ManyToOne
	private Collection collection;
	
	@Column(name = "isAccess")
	private boolean isAccess;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Collection getCollection() {
		return collection;
	}

	public void setCollection(Collection collection) {
		this.collection = collection;
	}

	public boolean isAccess() {
		return isAccess;
	}

	public void setAccess(boolean isAccess) {
		this.isAccess = isAccess;
	}
	
	
}
