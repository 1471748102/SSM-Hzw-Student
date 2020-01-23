package com.student.hzw.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.student.hzw.utils.CpachaUtil;


/**
 * 系统主页控制器
 * @author Hzw
 *
 */

@RequestMapping("/system")
@Controller
public class SystemController {
	
	@RequestMapping(value = "/index",method=RequestMethod.GET)
	public ModelAndView index(ModelAndView model) {
		model.setViewName("hello");
		model.addObject("user","你好，世界");
		return model;
	}
	
	/**
	 * 登录页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login",method=RequestMethod.GET)
	public ModelAndView login(ModelAndView model) {
		model.setViewName("system/login");
		//model.addObject("user","你好，世界");
		return model;
	}
	
	/**
	 * 登陆表单提交
	 * @return
	 */
	@RequestMapping(value = "/login",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> login(
			@RequestParam(value="username",required=true) String username,
			@RequestParam(value="password",required=true) String password,
			HttpServletRequest request
			){	
	Map<String,String> ret = new HashMap<String,String>();
	if(StringUtils.isEmpty(username)){
		ret.put("type", "error");
		ret.put("msg", "用户名不能为空!");
		return ret;
	}
	if(StringUtils.isEmpty(password)){
		ret.put("type", "error");
		ret.put("msg", "密码不能为空!");
		return ret;
	}
	ret.put("type", "success");
	ret.put("msg", "登录成功!");
		return ret;
}


/**
 * 显示验证码
 * @param request
 * @param vl
 * @param w
 * @param h
 * @param response
 */
	@RequestMapping(value = "/get_cpacha",method=RequestMethod.GET)
	public void getCpacha(HttpServletRequest request,
			@RequestParam(value="vl",defaultValue="4",required=false) Integer vl,
			@RequestParam(value="w",defaultValue="98",required=false) Integer w,
			@RequestParam(value="h",defaultValue="33",required=false) Integer h,
			HttpServletResponse response) {
		CpachaUtil cpachaUtil = new CpachaUtil(vl,w,h);
		String generatorVCode = cpachaUtil.generatorVCode();
		request.getSession().setAttribute("loginCpacha", generatorVCode);
		BufferedImage generatorRotateVCodeImage = cpachaUtil.generatorRotateVCodeImage(generatorVCode,true);//干扰线
		try {
			ImageIO.write(generatorRotateVCodeImage, "gif", response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("获取验证码");
	}

}
