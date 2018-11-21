package com.bear.demo.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Y.bear
 * @version 创建时间：2018年11月18日 下午1:13:33 类说明
 */
@RestController
public class HelloController {
	@GetMapping("/hello")
	public String hello() {
		return "hello world security";
	}
}
