package com.dd.blog.service;

import java.util.List;

import com.dd.blog.pojo.Blog;

public interface BlogService {

	public List<Blog> getAllBlogsbyUserId(Integer userId);

	public int deleteBlog(Integer blogid);

	public List<Blog> getAllBlogs();

	public List<Blog> getAllBlogsAdmin();

	public List<Blog> getLikeT(String title);

	public Blog getABlogbyId(Integer blogId);

	public int saveblog(Blog blog);

}
