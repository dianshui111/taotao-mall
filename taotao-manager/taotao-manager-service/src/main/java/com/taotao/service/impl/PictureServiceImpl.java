package com.taotao.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.utils.FtpUtil;
import com.taotao.common.utils.IDUtils;
import com.taotao.service.PictureService;

/**
 * 图片上传service
 * @author cbq
 *
 */
@Service
public class PictureServiceImpl implements PictureService {
//从配置文件中读入参数
	
	@Value("${FTP_ADDRESS}")
	private String FTP_ADDRESS;
	@Value("${FTP_PORT}")
	private Integer FTP_PORT;
	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;
	@Value("${FTP_PASSWORE}")
	private String FTP_PASSWORE;
	@Value("${FTP_BASEPATH}")
	private String FTP_BASEPATH;
	@Value("${IMAGE_BASIC_URL}")
	private String IMAGE_BASIC_URL;

	
	@Override
	public Map uploadPicture(MultipartFile uploadFile) {
		Map resultMap=new HashMap<>();
		try {
			//生成一个新的文件名，取原始文件名的扩展名，以及一定的策略产生的新名字（时间+随机数）
			String oldName=uploadFile.getOriginalFilename();
			String newName=IDUtils.genImageName()+oldName.substring(oldName.lastIndexOf("."));

			String imagePath=new DateTime().toString("/yyyy/MM/dd");
			//上传图片,根据日期来生成文件夹的明确路径，以提升存取时的性能
			boolean result=FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORE,FTP_BASEPATH,
					imagePath, newName, uploadFile.getInputStream());
			if(!result) {
				resultMap.put("error",1);
				resultMap.put("Message","上传图片失败");
				return resultMap;	
			}
			resultMap.put("error",0);
			resultMap.put("url",IMAGE_BASIC_URL+imagePath+"/"+newName);
			
		} catch (Exception e) {
			resultMap.put("error",1);
			resultMap.put("Message","上传图片发生异常");
			return resultMap;	
		}
		
		//返回处理结果
		//成功：

		//失败：
		
		
		
		return null;
	}

}
