/**
 * 
 */
package com.bear.demo.web.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Y.bear
 * @version 创建时间：2018年11月20日 下午8:50:48 类说明
 */
@Component
public class TimeInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("preHandle");
		if (handler instanceof HandlerMethod) {
			System.out.println("preHandle:"+((HandlerMethod) handler).getBean().getClass().getName());
			System.out.println("preHandle:"+((HandlerMethod) handler).getMethod().getName());
		}
		request.setAttribute("startTime", new Date().getTime());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle");
		long start = (long) request.getAttribute("startTime");
		System.out.println("time inteceptor:" + "耗时" + (new Date().getTime() - start));
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("afterCompletion");
		long start = (long) request.getAttribute("startTime");
		System.out.println("time inteceptor:" + "耗时" + (new Date().getTime() - start));
		System.out.println("ex is:" + ex);
	}

}
