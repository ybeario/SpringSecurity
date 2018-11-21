package com.bear.demo.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.bear.demo.utils.JsonUtils;

/**
 * @author Y.bear
 * @version 创建时间：2018年11月19日 上午11:08:54 类说明
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testUser() throws Exception {
		String responseString = mockMvc
				.perform(get("/user").contentType(MediaType.APPLICATION_JSON_UTF8).param("username", "Tom")
						.param("password", "456"))
				.andExpect(status().isOk()).andDo(print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
		System.out.println("返回的值：" + responseString);
		;
	}

	@Test
	public void testUserQuery() throws Exception {
		String responseString = mockMvc.perform(get("/user/query").contentType(MediaType.APPLICATION_JSON_UTF8)
				.param("username", "Tom").param("password", "456").param("age", "18").param("sex", "male")
		// .param("size", "15")
		// .param("page", "3")
		// .param("sort", "age,desc")
		).andExpect(status().isOk()).andExpect(jsonPath("$.length()").value(3)).andDo(print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
		System.out.println("返回的值：" + responseString);
		;
	}

	@Test
	public void testUserInfo() throws Exception {
		mockMvc.perform(get("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
				.andExpect(jsonPath("$.username").value("tom")).andDo(print());
	}

	@Test
	public void testUserInfoFail() throws Exception {
		mockMvc.perform(get("/user/a").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().is4xxClientError()).andDo(print());
	}

	@Test
	public void testCreateUser() throws Exception {
		Map<String, String> body = new HashMap<>();
		body.put("username", "bill");
		body.put("password", "");
		body.put("registday", String.valueOf(new Date().getTime())); // 前后台交互通过时间戳进行，后台只需保存时间戳，具体展示形式交给前台
		String content = JsonUtils.ToJson(body);
		mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON_UTF8).content(content))
				.andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void testUpdateUser() throws Exception {
		Map<String, String> body = new HashMap<>();
		body.put("id", "1");
		body.put("username", "bill");
		body.put("password", "");
		body.put("registday", String.valueOf(new Date().getTime()));
		String content = JsonUtils.ToJson(body);
		mockMvc.perform(put("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8).content(content))
				.andExpect(status().isOk());
	}

	@Test
	public void testUserNotExsitError() throws Exception {
		mockMvc.perform(get("/user/exception/1a").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().is5xxServerError());
	}

}
