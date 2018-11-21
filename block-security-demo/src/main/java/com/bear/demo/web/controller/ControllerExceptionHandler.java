package com.bear.demo.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bear.demo.exception.UserNotExistException;

/**
 * @author Y.bear
 * @version 创建时间：2018年11月20日 下午7:46:43 类说明
 */
@ControllerAdvice
public class ControllerExceptionHandler {
	@ExceptionHandler(UserNotExistException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String, Object> handleUserNotExistException(UserNotExistException exception) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", exception.getId());
		map.put("message", exception.getMessage());
		return map;

	}
}
