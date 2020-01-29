package com.student.hzw.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import com.student.hzw.utils.CpachaUtil;
import com.student.hzw.entity.User;
import com.student.hzw.service.UserService;
import com.student.hzw.service.impl.UserServiceImpl;



/**
 * ϵͳ��ҳ������
 * @author Hzw
 *
 */

@RequestMapping("/system")
@Controller
public class SystemController {

	//@Autowired
	
	
	
	
	@RequestMapping(value = "/index",method=RequestMethod.GET)
	public ModelAndView index(ModelAndView model) {
		model.setViewName("hello");
		model.addObject("user","��ã�����");
		return model;
	}
	
	/**
	 * ��¼ҳ��
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login",method=RequestMethod.GET)
	public ModelAndView login(ModelAndView model) {
		model.setViewName("system/login");
		//model.addObject("user","��ã�����");
		return model;
	}
	
	/**
	 * ��½���ύ
	 * @return
	 */
	@RequestMapping(value = "/login",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> login(
			@RequestParam(value="username",required=true) String username,
			@RequestParam(value="password",required=true) String password,
			@RequestParam(value="vcode",required=true) String vcode,
			@RequestParam(value="type",required=true) int type,
			HttpServletRequest request
			){	
	Map<String,String> ret = new HashMap<String,String>();
	if(StringUtils.isEmpty(username)){
		ret.put("type", "error");
		ret.put("msg", "�û�������Ϊ��!");
		return ret;
	}
	if(StringUtils.isEmpty(password)){
		ret.put("type", "error");
		ret.put("msg", "���벻��Ϊ��!");
		return ret;
	}
	if(StringUtils.isEmpty(vcode)){
		ret.put("type", "error");
		ret.put("msg", "��֤�벻��Ϊ��!");
		return ret;
	}
	String loginCpacha = (String)request.getSession().getAttribute("loginCpacha");
	if(StringUtils.isEmpty(loginCpacha)){
		ret.put("type", "error");
		ret.put("msg", "��ʱ��δ�������Ự��ʧЧ����ˢ�º�����!");
		return ret;
	}
	if(!vcode.toUpperCase().equals(loginCpacha.toUpperCase())){
		ret.put("type", "error");
		ret.put("msg", "��֤�����!");
		return ret;
	}
	request.getSession().setAttribute("loginCpacha", null);
	//�����ݿ���ȥ�����û�
	/*
	UserService userService = new UserServiceImpl();
	User user  = userService.findByUserName(username);
	if(user == null){
		ret.put("type", "error");
		ret.put("msg", "�����ڸ��û�!");
		return ret;
	}
	*/
	ret.put("type", "success");
	ret.put("msg", "��¼�ɹ�!");
		return ret;
}


/**
 * ��ʾ��֤��
 * @param request
 * @param vl
 * @param w
 * @param h
 * @param response
 */
	@RequestMapping(value = "/get_cpacha",method=RequestMethod.GET)
	public void getCpacha(HttpServletRequest request,
			@RequestParam(value="vl",defaultValue="2",required=false) Integer vl,
			@RequestParam(value="w",defaultValue="98",required=false) Integer w,
			@RequestParam(value="h",defaultValue="33",required=false) Integer h,
			HttpServletResponse response) {
		CpachaUtil cpachaUtil = new CpachaUtil(vl,w,h);
		String generatorVCode = cpachaUtil.generatorVCode();
		request.getSession().setAttribute("loginCpacha", generatorVCode);
		BufferedImage generatorRotateVCodeImage = cpachaUtil.generatorRotateVCodeImage(generatorVCode,true);//������
		try {
			ImageIO.write(generatorRotateVCodeImage, "gif", response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("��ȡ��֤��");
	}

}
