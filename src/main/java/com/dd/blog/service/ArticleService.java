package com.dd.blog.service;

import java.util.List;

import com.dd.blog.pojo.Blog;

public interface ArticleService {

	public List getComments(Integer blogid);

	public int updateArtl(Blog blog);

}
