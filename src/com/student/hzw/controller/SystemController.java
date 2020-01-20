package com.student.hzw.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	
	@RequestMapping(value = "/login",method=RequestMethod.GET)
	public ModelAndView login(ModelAndView model) {
		model.setViewName("system/login");
		//model.addObject("user","你好，世界");
		return model;
	}
	
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
