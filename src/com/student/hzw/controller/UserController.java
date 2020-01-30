package com.student.hzw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * ÓÃ»§¿ØÖÆÆ÷
 * @author Hzw
 *
 */
@RequestMapping("/user")
@Controller
public class UserController {
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		model.setViewName("user/user_list");
		return model;
	}
}
