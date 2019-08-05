package com.dd.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dd.blog.dao.CommentMapper;
import com.dd.blog.pojo.Comment;
import com.dd.blog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentMapper commentMapper;

	@Override
	public int insertComt(Comment comment) {
		// TODO Auto-generated method stub
		int cont = commentMapper.insert(comment);
		return cont;
	}

}
