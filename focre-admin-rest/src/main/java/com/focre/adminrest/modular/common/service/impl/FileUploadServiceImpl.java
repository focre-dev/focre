package com.focre.adminrest.modular.common.service.impl;

import com.focre.adminrest.config.RestProperties;
import com.focre.adminrest.modular.common.FileUploadService;
import com.focre.base.exception.BizExceptionEnum;
import com.focre.base.exception.BusinessException;
import com.focre.base.i18n.consts.CommonMessage;
import com.focre.utlis.util.DateUtil;
import com.focre.utlis.util.FileUtil;
import com.focre.utlis.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @ClassName: FileUploadServiceImpl
 * @Description: 文件上传实现
 * @Author ye21st
 * @Date 2020/4/9 11:57 上午:22
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FileUploadServiceImpl implements FileUploadService {

	protected Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RestProperties restProperties;

	/**
	 * @description [检查文件是否合法]
	 * @title fileCheck
	 * @author ye21st
	 * @date 2020/4/9
	 * @time 3:07 下午
	 **/
	@Override
	public void fileCheck(MultipartFile file) throws Exception {
		if (file.isEmpty()){
			log.error("上传文件操作 --- 文件上传为空,file.isEmpty():{}", file.isEmpty());
			throw new BusinessException(BizExceptionEnum.FAILURE, CommonMessage.FILE_UPLOAD_IS_EMPTY);
		}
		// 判断文件是否合法()通过后台配置判断

	}

	@Override
	public String uploadFile(MultipartFile file) throws Exception {
		// 调用文件属性检查方法
		fileCheck(file);

		// 根据系统设置文件存储位置来存储文件

		// 创建本地文件
		StringBuilder filename = new StringBuilder();
		filename.append(DateUtil.formatAllDate())
				.append(StringUtil.generateRandomString(6))
				.append(".")
				.append(FileUtil.getFileType(file));
		String filePath = restProperties.getFilePath().concat(FileUtil.getNowDateString());
		File dir = new File(filePath);
		// 判断目录是否存在
		if (!dir.exists() && !dir.isDirectory()) {
			dir.mkdirs();
		}
		File localFile = new File(filePath, String.valueOf(filename));
		// 把传上来的文件写到本地文件
		file.transferTo(localFile);
		return FileUtil.getNowDateString() + "/" + filename;
	}
}
