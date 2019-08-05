package com.dd.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dd.blog.pojo.Blog;
import com.dd.blog.pojo.BlogExample;

public interface BlogMapper {
	int countByExample(BlogExample example);

	int deleteByExample(BlogExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(Blog record);

	int insertSelective(Blog record);

	List<Blog> selectByExampleWithBLOBs(BlogExample example);

	List<Blog> selectByExample(BlogExample example);

	List<Blog> selectByExampleWithUser();

	List<Blog> selectByExampleWithUserAdmin();

	List<Blog> getLikeT(String title);

	Blog selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") Blog record, @Param("example") BlogExample example);

	int updateByExampleWithBLOBs(@Param("record") Blog record, @Param("example") BlogExample example);

	int updateByExample(@Param("record") Blog record, @Param("example") BlogExample example);

	int updateByPrimaryKeySelective(Blog record);

	int updateByPrimaryKeyWithBLOBs(Blog record);

	int updateByPrimaryKey(Blog record);
}