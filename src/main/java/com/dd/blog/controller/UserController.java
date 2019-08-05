package com.dd.blog.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dd.blog.pojo.User;
import com.dd.blog.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping("/saveuser")
	@ResponseBody
	public Map<String, Object> saveUser(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String username = request.getParameter("username");
		// System.out.println("-----------" + username);
		String password = request.getParameter("password");
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		int effectNum = userService.saveUser(user);
		if (effectNum > 0) {
			map.put("success", true);
		} else {
			map.put("success", false);
		}
		return map;
	}

	@RequestMapping("/getuser")
	@ResponseBody
	public Map<String, Object> getUserList(HttpServletRequest request) {
		List<User> userList = new ArrayList<User>();
		Map<String, Object> map = new HashMap<String, Object>();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// System.out.println("1----" + password);
		String url = (String) request.getSession().getAttribute("url");
		userList = userService.getUserList(username);
		// System.out.println("2----" + userList.get(0).getPassword());
		if (userList.get(0).getPassword().equals(password) == true) {
			if (userList.get(0).getUsername().equals("admin") == true) {
				map.put("admin", true);
			} else {
				map.put("admin", false);
			}
			map.put("success", true);
			map.put("url", url);
			map.put("count", userList.size());
			request.getSession().setAttribute("currentuser", userList.get(0));
		} else {
			map.put("success", false);
		}
		return map;
	}
}
