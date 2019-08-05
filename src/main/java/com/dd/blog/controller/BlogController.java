package com.dd.blog.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dd.blog.pojo.Blog;
import com.dd.blog.pojo.User;
import com.dd.blog.service.ArticleService;
import com.dd.blog.service.BlogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class BlogController {

	@Autowired
	BlogService blogService;
	@Autowired
	ArticleService articleService;

	@RequestMapping("/blogsadmin")
	@ResponseBody
	public Map<String, Object> getBlogsadmin(@RequestParam(value = "pn", defaultValue = "1") Integer pn,
										HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		User user = (User) request.getSession().getAttribute("currentuser");
		if (user == null) {
			map.put("login", false);
		} else {
			map.put("login", true);
			map.put("currentuser", user);
		}
		PageHelper.startPage(pn, 5);
		List<Blog> blogs = blogService.getAllBlogsAdmin();
		PageInfo page = new PageInfo(blogs, 5);
		// 带用户的联合查询
		map.put("pageInfo", page);
		return map;
	}
	@RequestMapping("/blogs2")
	@ResponseBody
	public Map<String, Object> getBlogs(@RequestParam(value = "pn", defaultValue = "1") Integer pn,
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		User user = (User) request.getSession().getAttribute("currentuser");
		if (user == null) {
			map.put("login", false);
		} else {
			map.put("login", true);
			map.put("currentuser", user);
		}
		PageHelper.startPage(pn, 5);
		List<Blog> blogs = blogService.getAllBlogs();
		PageInfo page = new PageInfo(blogs, 5);
		// 带用户的联合查询
		map.put("pageInfo", page);
		return map;
	}

	@RequestMapping("/blogs")
	@ResponseBody
	public Map<String, Object> getBlogsu(@RequestParam(value = "pn", defaultValue = "1") Integer pn,
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		User user = (User) request.getSession().getAttribute("currentuser");
		PageHelper.startPage(pn, 5);
		List<Blog> blogs = blogService.getAllBlogsbyUserId(user.getId());

		PageInfo page = new PageInfo(blogs, 5);
		map.put("pageInfo", page);
		map.put("user", user);
		return map;
	}

	@RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> delBlog(@PathVariable("id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer blogid = Integer.parseInt(id);
		int cont = blogService.deleteBlog(blogid);
		String msg;
		if (cont > 0) {
			msg = "处理成功";
		} else {
			msg = "处理失败";
		}
		map.put("msg", msg);
		return map;
	}

	@RequestMapping("/search")
	@ResponseBody
	public Map<String, Object> searchTitle(@RequestParam(value = "pn", defaultValue = "1") Integer pn,
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String title = request.getParameter("search_title");
		PageHelper.startPage(pn, 5);
		List<Blog> blogs = blogService.getLikeT(title);
		PageInfo page = new PageInfo(blogs, 5);
		map.put("pageInfo", page);
		return map;
	}

	@RequestMapping("/saveblog")
	@ResponseBody
	public Map<String, Object> saveblog(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		User currentuser = (User) request.getSession().getAttribute("currentuser");
		Blog blog = new Blog();
		blog.setTitle(title);
		blog.setContent(content);
		blog.setUserId(currentuser.getId());
		blog.setState(0);
		blog.setUpTime(new Date());
		int effectedNum = blogService.saveblog(blog);
		if (effectedNum > 0) {
			map.put("success", true);
		} else {
			map.put("success", false);
		}
		return map;
	}

	@RequestMapping("/updatestate")
	@ResponseBody
	public void updatestate(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer blogid = Integer.parseInt(request.getParameter("blogid"));
		Integer state = Integer.parseInt(request.getParameter("state"));
		Blog blog = new Blog();
		blog.setId(blogid);
		blog.setState(state);
		articleService.updateArtl(blog);
	}
}
