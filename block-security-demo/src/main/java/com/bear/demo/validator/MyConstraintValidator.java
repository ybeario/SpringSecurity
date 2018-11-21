package com.bear.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.bear.demo.service.HelloService;

/**
 * @author Y.bear
 * @version 创建时间：2018年11月20日 下午4:45:52 类说明
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {
	@Autowired
	private HelloService helloService;

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		System.out.println(helloService.greeting("tom"));
		System.out.println(value);
		return false;
	}

}
