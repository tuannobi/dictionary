package com.tuan.dictionary.collection;

import com.tuan.dictionary.base.BaseService;
import com.tuan.dictionary.base.BaseServiceImpl;

public interface CollectionService extends BaseService<Collection, String> {
	void addCollection(Collection collection);
	void updateCollection(Collection collection);
}
