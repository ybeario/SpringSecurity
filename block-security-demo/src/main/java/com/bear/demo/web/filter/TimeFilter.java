package com.bear.demo.web.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.stereotype.Component;

/**
 * @author Y.bear
 * @version 创建时间：2018年11月20日 下午8:07:20 类说明
 */
@Component // 将实现了Filter接口的类作为组件，那么会自动应用于全部url("/*") 与手动配置相比，失去灵活性
public class TimeFilter implements Filter {

	@Override
	public void destroy() {
		System.out.println("time filter destroy");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		System.out.println("time filter start");
		long start = new Date().getTime();
		filterChain.doFilter(request, response);
		System.out.println("time filter:" + "耗时" + (new Date().getTime() - start));
		System.out.println("time filter finish");

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("time filter init");
	}
}
