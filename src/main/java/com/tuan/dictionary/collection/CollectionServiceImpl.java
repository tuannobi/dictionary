package com.tuan.dictionary.collection;

import java.time.LocalDateTime;
import java.util.Optional;

import com.tuan.dictionary.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.tuan.dictionary.base.BaseServiceImpl;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CollectionServiceImpl extends BaseServiceImpl<Collection, Long> implements CollectionService {
	
	private final CollectionRepository collectionRepository;
	
	@Autowired
	public CollectionServiceImpl(CollectionRepository collectionRepository) {
		this.collectionRepository=collectionRepository;
	}

	@Override
	protected CrudRepository<Collection, Long> getRepository() {
		return collectionRepository;
	}
	
	@Override
	@Transactional
	public void addCollection(Collection collection) {
		collection.setCreateDate(LocalDateTime.now());
		collectionRepository.save(collection);
	}

	@Override
	@Transactional
	public void updateCollection(Collection collection) throws ServiceException {
		Optional<Collection> existingCollection=collectionRepository.findById(collection.getId());
		if(!existingCollection.isPresent()){
			throw new ServiceException("Collection Not Found");
		}
		collectionRepository.update(collection.getId(),collection.getName(),collection.getDescription(),LocalDateTime.now(),collection.isAccess(),collection.getImageNames());
	}

	@Override
	@Transactional(readOnly = true)
	public String getUrlImageById(Long id) {
		return collectionRepository.getUrlImageById(id);
	}

	@Override
	@Transactional
	public void updateExcludeImage(Collection collection) {
		Optional<Collection> existingCollection=collectionRepository.findById(collection.getId());
		if(!existingCollection.isPresent()){
			throw new ServiceException("Collection Not Found");
		}
		collectionRepository.updateExcludeImage(collection.getId(),collection.getName(),collection.getDescription(),LocalDateTime.now(),collection.isAccess());
		
	}

}
