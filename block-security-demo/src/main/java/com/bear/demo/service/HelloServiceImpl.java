package com.bear.demo.service;

import org.springframework.stereotype.Service;

/**
 * @author Y.bear
 * @version 创建时间：2018年11月20日 下午4:50:05 类说明
 */
@Service
public class HelloServiceImpl implements HelloService {

	@Override
	public String greeting(String name) {
		return "hello!" + name;
	}

}
