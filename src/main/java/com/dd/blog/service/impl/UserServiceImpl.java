package com.dd.blog.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dd.blog.dao.UserMapper;
import com.dd.blog.pojo.User;
import com.dd.blog.pojo.UserExample;
import com.dd.blog.pojo.UserExample.Criteria;
import com.dd.blog.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public int saveUser(User user) {
		// TODO Auto-generated method stub
		return userMapper.insert(user);
	}

	@Override
	public List<User> getUserList(String username) {
		// TODO Auto-generated method stub
		List<User> userList = new ArrayList<User>();
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		userList = userMapper.selectByExample(example);
		return userList;
	}

}
