package com.dd.blog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dd.blog.pojo.User;

@Controller
public class PageController {
	@RequestMapping("/register")
	public String register() {
		return "register";
	}

	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		String url = request.getParameter("url");
		request.getSession().setAttribute("url", url);
		return "login";
	}

	@RequestMapping("/personal")
	public String personal(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("currentuser");
		if (user == null) {
			return "redirect:login";
		} else {
			return "personal";
		}
	}

	@RequestMapping("/article")
	public String article(HttpServletRequest request) {
		String blogid = request.getParameter("blogid");
		request.getSession().setAttribute("blogid", blogid);
		return "article";
	}

	@RequestMapping("/article2")
	public String article2(HttpServletRequest request) {
		String blogid = request.getParameter("blogid2");
		request.getSession().setAttribute("blogid", blogid);
		return "article2";
	}

	@RequestMapping("/texteditor")
	public String texteditor(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("currentuser");
		if (user == null) {
			return "redirect:login";
		} else {
			return "texteditor";
		}
	}

	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@RequestMapping("/admin")
	public String admin(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("currentuser");
		if (user == null) {
			return "redirect:login";
		} else {
			return "admin";
		}
	}
}
