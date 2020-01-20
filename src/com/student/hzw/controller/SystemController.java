package com.student.hzw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
		return model;
	}

}
