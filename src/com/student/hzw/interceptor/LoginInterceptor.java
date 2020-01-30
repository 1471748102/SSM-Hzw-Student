package com.student.hzw.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONObject;

/*
 * 实现登陆拦截
 */
public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		//在之前拦截
		String url = request.getRequestURI();
		System.out.println("拦截器拦截地址，url = " + url);
		Object user = request.getSession().getAttribute("user");
		if(user == null){
			//表示未登录或者登录状态失效
			System.out.println("未登录或登录失效，url = " + url);
			if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
				//ajax请求
				Map<String, String> ret = new HashMap<String, String>();
				ret.put("type", "error");
				ret.put("msg", "登录状态已失效，请重新去登录!");
				response.getWriter().write(JSONObject.fromObject(ret).toString());
				return false;
			}
			//普通请求跳转请求
			response.sendRedirect(request.getContextPath() + "/system/login");
			return false;
		}
		return true;
	
	}

}
