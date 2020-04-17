package com.focre.service.common.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @description [文件上传]
 * @title
 * @author ye21st
 * @date 2020/4/9
 * @time 10:33 上午
 **/
public interface FileUploadService {

	/**
	 * @description [检查文件是否合法]
	 * @title fileCheck
	 * @author ye21st
	 * @date 2020/4/9
	 * @time 3:07 下午
	 **/
	void fileCheck(MultipartFile file) throws Exception;

	/**
	 * @description [上传文件方法]
	 * @title uploadFile
	 * @author ye21st
	 * @date 2020/4/9
	 * @time 3:34 下午
	 * @param file []
	 * @return java.lang.String
	 **/
	String uploadFile(MultipartFile file) throws Exception;

}
