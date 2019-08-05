package com.dd.blog.dao;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dd.blog.pojo.Blog;
import com.dd.blog.pojo.Comment;
import com.dd.blog.pojo.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class testDao {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private BlogMapper blogMapper;
	@Autowired
	private CommentMapper commentMapper;

	@Test
	public void testUserDao() {
		User user = new User();
		user.setUsername("test1");
		user.setPassword("test1");
		int effectedNum = userMapper.insert(user);
		assertEquals(effectedNum, 1);
	}

	@Test
	public void testBlogWithUser() {
		List<Blog> blogs = blogMapper.selectByExampleWithUser();
		for (Blog blog : blogs) {
			System.out.println(blog.getTitle());
			System.out.println(blog.getUser().getUsername());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			System.out.println(sdf.format(blog.getUpTime()));
			System.out.println(blog.getState());
		}
	}

	@Test
	public void testBlogSearch() {
		List<Blog> blogs = blogMapper.getLikeT("1");
		for (Blog blog : blogs) {
			System.out.println(blog.getTitle());
			System.out.println(blog.getUser().getUsername());
		}
	}

	@Test
	public void testInsertComt() {
		Comment comment = new Comment();
		comment.setBlogId(2);
		comment.setContent("222");
		comment.setUserId(1);
		int cout = commentMapper.insert(comment);
		System.out.println(cout);
	}
}
