package com.student.hzw.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.student.hzw.entity.User;
import com.student.hzw.page.Page;
import com.student.hzw.service.UserService;

/**
 * �û�������
 * @author Hzw
 *
 */
@RequestMapping("/user")
@Controller
public class UserController {
	
	@Autowired
	public UserService userService;
	
	/*
	 * �û������б�ҳ
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		model.setViewName("user/user_list");
		return model;
	}
	/*
	 * ��ȡ�û�����
	 */
	@RequestMapping(value="/get_list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getList(
			@RequestParam(value="username",required=false,defaultValue="") String username,
			Page page
			){
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("username", "%"+username+"%");
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows", userService.findList(queryMap));
		ret.put("total", userService.getTotal(queryMap));
		return ret;
	}
	/*
	 * ����û�����
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(User user){
		Map<String, String> ret = new HashMap<String, String>();
		if(user == null){
			ret.put("type", "error");
			ret.put("msg", "���ݰ󶨳���");
			return ret;
		}
		if(StringUtils.isEmpty(user.getUsername())){
			ret.put("type", "error");
			ret.put("msg", "�û�������Ϊ��!");
			return ret;
		}
		if(StringUtils.isEmpty(user.getPassword())){
			ret.put("type", "error");
			ret.put("msg", "���벻��Ϊ��!");
			return ret;
		}
		User existUser = userService.findByUserName(user.getUsername());
		if(existUser != null){
			ret.put("type", "error");
			ret.put("msg", "���û����Ѿ�����!");
			return ret;
		}
		if(userService.add(user) <= 0){
			ret.put("type", "error");
			ret.put("msg", "���ʧ��!");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "��ӳɹ�!");
		return ret;
	}
}
