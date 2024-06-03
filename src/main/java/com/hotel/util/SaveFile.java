package com.hotel.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class SaveFile {
	public String saveFile(MultipartFile file) {
		if(file!=null &&!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				//lấy biến môi trường
				String rootPath = "E:\\Spring MVC\\hotel\\src\\main\\webapp\\";
				//Tạo folder để lưu file
				//File.separator giúp biên dịch "/"
				File dir = new File(rootPath+File.separator+"/template/admin/img/room");
				if(!dir.exists()) {
					//tự động tạo folder
					dir.mkdir();
				}
				//Creating the file on server
				String name = file.getOriginalFilename();
//				String name = String.valueOf(new Date().getTime()+".jpg");
				File serverFile = new File(dir.getAbsoluteFile()+File.separator+name);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				return name;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("lỗi"+e.getMessage());
			}
		}
		else {
			System.out.println("lỗi file");
		}
		
		return null;
	}
	public String saveFileAccount(MultipartFile file) {
		if(file!=null &&!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				//lấy biến môi trường
				String rootPath = "E:\\Spring MVC\\hotel\\src\\main\\webapp\\";
				//Tạo folder để lưu file
				//File.separator giúp biên dịch "/"
				File dir = new File(rootPath+File.separator+"/template/admin/img/account");
				if(!dir.exists()) {
					//tự động tạo folder
					dir.mkdir();
				}
				//Creating the file on server
				String name = file.getOriginalFilename();
				File serverFile = new File(dir.getAbsoluteFile()+File.separator+name);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				return name;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("lỗi"+e.getMessage());
			}
		}
		else {
			System.out.println("lỗi file");
		}
		
		return null;
	}
}
