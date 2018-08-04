package com.taotao.controller;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import com.taotao.common.utils.FtpUtil;

public class TestFTPUtil {
	@Test
	public void testFtpClient()throws Exception {
		//建立一个ftpClient对象
		FTPClient ftpClient=new FTPClient();
		//连接到ftp服务器,第一个参数为网址，第二个参数为端口号
		ftpClient.connect("192.168.57.130", 21);
		//输入用户名，密码
		ftpClient.login("ftpuser", "ilovechen");
		//指定文件上传位置以及文件类型（二进制文件）
		ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		//将本地文件以io流的形式，传到服务器
		FileInputStream fileInputStream=new FileInputStream(new File("D:\\picture\\timg2W59KSN0.jpg"));
		//上传文件,保存的文件名，
		ftpClient.storeFile("hello.jpg", fileInputStream);
		//退出连接
		ftpClient.logout();
	}
	
	@Test
	public void testFtpUtils()throws Exception{
		FileInputStream fileInputStream=new FileInputStream(new File("D:\\picture\\timg2W59KSN0.jpg"));
		FtpUtil.uploadFile("192.168.57.130", 21, "ftpuser", "ilovechen", "/home/ftpuser/www/images", "/8/2", "hello1", fileInputStream);
		
	}

}
