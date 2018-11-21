package com.bear.demo.dto;

/**
 * @author Y.bear
 * @version 创建时间：2018年11月19日 上午10:44:47 类说明
 */
public class UserQueryCommand {
	private String username;
	private String password;
	private int age;
	private String sex;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "UserQueryCommand [username=" + username + ", password=" + password + ", age=" + age + ", sex=" + sex
				+ "]";
	}

}
