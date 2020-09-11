package com.tuan.dictionary.collection;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.tuan.dictionary.base.BaseServiceImpl;

@Service
public class CollectionServiceImpl extends BaseServiceImpl<Collection, String> implements CollectionService {
	
	private CollectionRepository collectionRepository;
	
	@Autowired
	public CollectionServiceImpl(CollectionRepository collectionRepository) {
		this.collectionRepository=collectionRepository;
	}

	@Override
	protected CrudRepository<Collection, String> getRepository() {
		// TODO Auto-generated method stub
		return collectionRepository;
	}
	
	
	public void addCollection(Collection collection) {
		collection.setCreateDate(LocalDateTime.now());
		collectionRepository.save(collection);
	}

	@Override
	public void updateCollection(Collection collection) {
		collection.setUpdateTime(LocalDateTime.now());
		collectionRepository.save(collection);
		
	}

}
