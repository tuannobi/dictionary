package com.tuan.dictionary.vocabularycollection;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tuan.dictionary.collection.Collection;
import com.tuan.dictionary.vocabulary.Vocabulary;

@Entity
@Table(name="vocabulary_collection")
public class VocabularyCollection implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	private Vocabulary vocabulary;
	
	@Id
	@ManyToOne
	private Collection collection;
	
	@Column(name = "addition_date")
	private LocalDateTime additionDate;

	public Vocabulary getVocabulary() {
		return vocabulary;
	}

	public void setVocabulary(Vocabulary vocabulary) {
		this.vocabulary = vocabulary;
	}

	public Collection getCollection() {
		return collection;
	}

	public void setCollection(Collection collection) {
		this.collection = collection;
	}

	public LocalDateTime getAdditionDate() {
		return additionDate;
	}

	public void setAdditionDate(LocalDateTime additionDate) {
		this.additionDate = additionDate;
	}
	
	
}
