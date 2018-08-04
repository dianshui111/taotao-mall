package com.taotao.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.utils.JsonUtils;
import com.taotao.service.PictureService;
/**
 * 图片上传controller
 * @author cbq
 *
 */
@Controller
@RequestMapping("/pic")
public class PictureController {
	
	@Autowired
	private PictureService pictureService;
	
	
	@RequestMapping("/upload")
	@ResponseBody
	public String uploadPicture(MultipartFile uploadFile) {
		Map resultMap=pictureService.uploadPicture(uploadFile);
		//为了保证功能的兼容性，需要把 resultMap转换成json格式的字符串
		String json=JsonUtils.objectToJson(resultMap);
		return json;
	}

}
