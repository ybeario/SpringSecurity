package com.bear.demo.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bear.demo.dto.FileInfo;

/**
 * @author Y.bear
 * @version 创建时间：2018年11月21日 下午4:34:58 类说明
 */
@RestController
@RequestMapping("/file")
public class FileController {
	@PostMapping
	public FileInfo upload(MultipartFile file) throws IllegalStateException, IOException {
		System.out.println(file.getName());
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
		String folder = "C:\\Users\\Y.bear\\eclipse-workspace\\block-security";
		File localFile = new File(folder, Calendar.getInstance().getTime().getTime() + ".txt");
		file.transferTo(localFile);
		return new FileInfo(localFile.getAbsolutePath());

	}

	@GetMapping("/{id}")
	public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String folder = "C:\\Users\\Y.bear\\eclipse-workspace\\block-security";
		FileInputStream fileInputStream = new FileInputStream(new File(folder, id + ".txt"));
		ServletOutputStream outputStream = response.getOutputStream();
		response.setContentType("application/x-download");
		response.addHeader("Content-Disposition", "attachment;filename=test.txt");
		IOUtils.copy(fileInputStream, outputStream);
		outputStream.flush();
	}
}
