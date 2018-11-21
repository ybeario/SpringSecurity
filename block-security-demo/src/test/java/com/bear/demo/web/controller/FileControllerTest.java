package com.bear.demo.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author Y.bear
 * @version 创建时间：2018年11月21日 下午4:35:15 类说明
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FileControllerTest {
	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testUpload() throws Exception {
		String content = mockMvc
				.perform(multipart("/file")
						.file(new MockMultipartFile("file", "test.txt", null, "hello upload".getBytes("UTF-8"))))
				// 字段名（比如前端的name=passsword）,文件名，文件类型，文件内容
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		System.out.println(content);
	}

}
