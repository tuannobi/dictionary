package com.tuan.dictionary.historyactiondetail;

import com.tuan.dictionary.collection.Collection;
import com.tuan.dictionary.user.User;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "history_action_detail")
public class HistoryActionDetail implements Serializable {
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

    @Column(name="action_date")
    private LocalDateTime actionDate;
    
    @Column(name="description")
    private String description;

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

	public LocalDateTime getActionDate() {
		return actionDate;
	}

	public void setActionDate(LocalDateTime actionDate) {
		this.actionDate = actionDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}    


}
