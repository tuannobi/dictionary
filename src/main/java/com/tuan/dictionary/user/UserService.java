package com.tuan.dictionary.user;

import com.tuan.dictionary.base.BaseService;
import com.tuan.dictionary.exception.ServiceException;

public interface UserService extends BaseService<User, Long> {
	void addClientUser(User user) throws ServiceException;
}
