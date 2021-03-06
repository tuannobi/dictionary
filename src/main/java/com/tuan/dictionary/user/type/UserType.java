package com.tuan.dictionary.user.type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_type")
public class UserType {
    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
   

    public UserType() {
		super();
	}

	public UserType(String name) {
		super();
		this.name = name;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
