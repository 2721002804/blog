package com.dd.blog.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dd.blog.dao.BlogMapper;
import com.dd.blog.pojo.Blog;
import com.dd.blog.pojo.BlogExample;
import com.dd.blog.service.BlogService;

@Service
public class BlogServiceImpl implements BlogService {
	@Autowired
	BlogMapper blogMapper;

	@Override
	public List<Blog> getAllBlogsbyUserId(Integer userId) {
		List<Blog> blogs = new ArrayList<Blog>();
		BlogExample blogExample = new BlogExample();
		BlogExample.Criteria criteria = blogExample.createCriteria();
		blogExample.setOrderByClause("id desc");
		criteria.andUserIdEqualTo(userId);
		blogs = blogMapper.selectByExampleWithBLOBs(blogExample);
		return blogs;
	}

	@Override
	public int deleteBlog(Integer blogid) {
		int cont = blogMapper.deleteByPrimaryKey(blogid);
		return cont;
	}

	@Override
	public List<Blog> getAllBlogs() {
		List<Blog> blogs = new ArrayList<Blog>();
		blogs = blogMapper.selectByExampleWithUser();
		return blogs;
	}

	@Override
	public List<Blog> getAllBlogsAdmin() {
		return blogMapper.selectByExampleWithUserAdmin();
	}

	@Override
	public List<Blog> getLikeT(String title) {
		// TODO Auto-generated method stub
		return blogMapper.getLikeT(title);
	}

	@Override
	public Blog getABlogbyId(Integer blogId) {
		// TODO Auto-generated method stub
		Blog blog = blogMapper.selectByPrimaryKey(blogId);
		return blog;
	}

	@Override
	public int saveblog(Blog blog) {
		// TODO Auto-generated method stub
		return blogMapper.insert(blog);
	}

}
