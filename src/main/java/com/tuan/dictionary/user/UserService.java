package com.tuan.dictionary.user;

import com.tuan.dictionary.base.BaseService;

public interface UserService extends BaseService<User, Long> {
	boolean addClientUser(User user);
}
