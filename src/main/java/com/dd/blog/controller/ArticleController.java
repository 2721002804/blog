package com.dd.blog.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dd.blog.pojo.Blog;
import com.dd.blog.pojo.Comment;
import com.dd.blog.pojo.User;
import com.dd.blog.service.ArticleService;
import com.dd.blog.service.BlogService;
import com.dd.blog.service.CommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class ArticleController {

	@Autowired
	private BlogService blogService;

	@Autowired
	private ArticleService articleService;

	@Autowired
	private CommentService commentService;

	// 评论验证
	@ResponseBody
	@RequestMapping("/yz")
	public Map getState(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		User user = (User) request.getSession().getAttribute("currentuser");
		String state;
		if (user != null) {
			state = "100";
		} else {
			state = "200";
		}
		map.put("state", state);
		return map;
	}

	@ResponseBody
	@RequestMapping("/spc")
	public Map getSpc(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer blogid = Integer.parseInt((String) request.getSession().getAttribute("blogid"));

		// 取文章
		String title, content;
		Blog blog = blogService.getABlogbyId(blogid);
		map.put("blog", blog);
		return map;
	}

	@ResponseBody
	@RequestMapping("comt")
	public Map getComments(@RequestParam(value = "pn", defaultValue = "1") Integer pn, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer blogid = Integer.parseInt((String) request.getSession().getAttribute("blogid"));
		PageHelper.startPage(pn, 5);
		// 取评论
		List<Comment> comments = new ArrayList<Comment>();
		comments = articleService.getComments(blogid);
		PageInfo page = new PageInfo(comments, 5);
		map.put("pageInfo", page);
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Map updateArticle(Blog blog, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer blogid = Integer.parseInt((String) request.getSession().getAttribute("blogid"));
		blog.setId(blogid);
		// System.out.println(blog);
		blog.setUpTime(new Date());
		int cont = articleService.updateArtl(blog);
		String state;
		if (cont > 0) {
			state = "100";
		} else {
			state = "200";
		}
		map.put("state", state);
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/insertC", method = RequestMethod.PUT)
	public Map insertComt(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String content = request.getParameter("comt");
		Integer blogid = Integer.parseInt((String) request.getSession().getAttribute("blogid"));
		User user = (User) request.getSession().getAttribute("currentuser");
		Integer userid = user.getId();
		Comment comment = new Comment();
		comment.setContent(content);
		comment.setBlogId(blogid);
		comment.setUserId(userid);
		int cont = commentService.insertComt(comment);
		String state;
		if (cont > 0) {
			state = "100";
		} else {
			state = "200";
		}
		map.put("state", state);
		return map;
	}
}
