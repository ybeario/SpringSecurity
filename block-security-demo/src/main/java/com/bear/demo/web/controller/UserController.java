package com.bear.demo.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bear.demo.dto.User;
import com.bear.demo.dto.User.UserDetailView;
import com.bear.demo.dto.User.UserSimpleView;
import com.bear.demo.dto.UserQueryCommand;
import com.bear.demo.exception.UserNotExistException;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * @author Y.bear
 * @version 创建时间：2018年11月19日 上午9:44:56 类说明
 */
@RestController
@RequestMapping("/user")
public class UserController {
	@GetMapping
	@JsonView(UserSimpleView.class)
	public List<User> query(@RequestParam(defaultValue = "admin", name = "username") String nickname,
			@RequestParam String password) {
		List<User> users = new ArrayList<>();
		users.add(new User(nickname, password));
		users.add(new User());
		users.add(new User());
		return users;
	}

	@GetMapping("/query")
	@JsonView(UserSimpleView.class)
	public List<User> userQuery(UserQueryCommand command,
			@PageableDefault(page = 2, size = 10, sort = "username,asc") Pageable pageable) {
//		System.out.println(ReflectionToStringBuilder.toString(command, ToStringStyle.MULTI_LINE_STYLE));
//		System.out.println(ReflectionToStringBuilder.toString(pageable, ToStringStyle.JSON_STYLE));
		List<User> list = new ArrayList<>();
		list.add(new User(command.getUsername(), command.getPassword()));
		list.add(new User());
		list.add(new User());
		return list;

	}

	/**
	 * 
	 * @param id
	 *            利用正则表达式完成对url的规范，只有符合要求的url能够通过
	 * @return
	 */
	@GetMapping("/{id:\\d+}")
	@JsonView(UserDetailView.class)
	public User queryUserInfo(@PathVariable String id) {
		User user = new User("tom", id);
		return user;
	}

	/**
	 * 
	 * @param user
	 * @param errors
	 *            当valid出现不符合要求的数据时，错误会保存在BindingResult对象中
	 * @RequestBody post方法中body里面的数据
	 * @return
	 */
	@PostMapping
	public User createUser(@Valid @RequestBody User user, BindingResult errors) {
		if (errors.hasErrors()) {
			errors.getAllErrors().stream().forEach((error) -> {
				System.out.println(error.getDefaultMessage());
			});
		}
//		System.out.println(ReflectionToStringBuilder.toString(user, ToStringStyle.JSON_STYLE));
		user.setId("1");
		return user;

	}

	@PutMapping("/{id:\\d+}")
	public User updateUser(@Valid @RequestBody User user, BindingResult errors) {
		if (errors.hasErrors()) {
			errors.getAllErrors().stream().forEach((error) -> {
				FieldError fieldError = (FieldError) error;
				String message = fieldError.getField() + ":" + error.getDefaultMessage();
				System.out.println(message);

			});
		}
//		System.out.println(ReflectionToStringBuilder.toString(user, ToStringStyle.JSON_STYLE));
		user.setId("1");
		return user;

	}

	@GetMapping("/exception/{id}")
	public void exception(@PathVariable String id) {
		throw new UserNotExistException(id);
	}
}
