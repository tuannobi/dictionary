package com.tuan.dictionary.collection;

import com.tuan.dictionary.base.BaseService;
import com.tuan.dictionary.base.BaseServiceImpl;
import com.tuan.dictionary.exception.ServiceException;

public interface CollectionService extends BaseService<Collection, Long> {
	void addCollection(Collection collection);
	void updateCollection(Collection collection) throws ServiceException;
}
