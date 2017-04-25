package com.sicnu.baby.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sicnu.baby.bean.User;


/**
 * 权限拦截器
 * @author bulala
 *
 */
public class JurisdictionInterceptor implements HandlerInterceptor{

	/*
	 *  提交请求被服务后执行
	 *  
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
	    
	}

	/*
	 *  提交请求被服务后执行
	 * model改变显示视图 
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView model)
			throws Exception {
	}

	/*
	 *  首先执行
	 *  返回值：表示是否将当前请求拦截
	 *  返回false请求终止
	 *  返回true请求继续运行 
	 *  Object 表示被拦截请求的目标的对象
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user.getUserType()==1){
			return true;
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.getWriter().println("{\"success\":false,\"error\":\"你无权进行此操作\"}");
		return false;
	}
}
