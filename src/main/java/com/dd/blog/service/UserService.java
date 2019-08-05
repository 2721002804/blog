package com.dd.blog.service;

import java.util.List;

import com.dd.blog.pojo.User;

public interface UserService {
	int saveUser(User user);

	List<User> getUserList(String username);
}
