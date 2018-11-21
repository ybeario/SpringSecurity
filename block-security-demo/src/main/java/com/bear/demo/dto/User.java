package com.bear.demo.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.bear.demo.validator.MyConstraint;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * @author Y.bear
 * @version 创建时间：2018年11月19日 上午9:47:05 类说明
 */
public class User {
	/**
	 * 
	 * 2018年11月20日
	 * 
	 * @author Y.bear
	 * @describe JsonView的使用方法步骤: 1.使用接口来声明多个视图 2.在值对象的get方法上指定视图
	 *           3.在controller方法上指定视图
	 */
	/*
	 * UserSimpleView在返回前端数据时，过滤password字段
	 */
	public interface UserSimpleView {
	};

	/*
	 * UserDetailView在返回前端数据时，在UserSimpleView中已有数据的基础上继续加入password等字段
	 */
	public interface UserDetailView extends UserSimpleView {
	};

	@NotBlank(message = "用户名不能为空")
	private String username;
	@NotBlank(message = "密码不能为空")
	private String password;
	@MyConstraint(message="注解测试")
	private String id;
	private Date registday;

	public User() {
		super();
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	@JsonView(UserSimpleView.class)
	public Date getRegistday() {
		return registday;
	}

	public void setRegistday(Date registday) {
		this.registday = registday;
	}

	@JsonView(UserSimpleView.class)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonView(UserSimpleView.class)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JsonView(UserDetailView.class)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
