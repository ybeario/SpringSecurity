package com.bear.demo.web.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bear.demo.web.filter.TimeFilter;
import com.bear.demo.web.interceptor.TimeInterceptor;

/**
 * @author Y.bear
 * @version 创建时间：2018年11月20日 下午8:14:50 类说明 手动注册filter为bean，该方法的好处是能够自由控制url
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Autowired
	private TimeInterceptor interceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptor);
	}

	@Bean
	public FilterRegistrationBean<TimeFilter> timeFilter() {
		FilterRegistrationBean<TimeFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		TimeFilter timeFilter = new TimeFilter();
		filterRegistrationBean.setFilter(timeFilter);
		List<String> urls = new ArrayList<>();
		urls.add("/user/*");
		filterRegistrationBean.setUrlPatterns(urls);
		return filterRegistrationBean;
	}
}
