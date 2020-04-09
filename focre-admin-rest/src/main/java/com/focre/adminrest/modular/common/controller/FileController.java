package com.focre.adminrest.modular.common.controller;

import com.focre.adminrest.modular.common.FileUploadService;
import com.focre.base.controller.BaseController;
import com.focre.base.entity.dto.ResultDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: FileController
 * @Description: 文件上传控制器
 * @Author ye21st
 * @Date 2020/4/9 10:09 上午:39
 */
@RestController
@RequestMapping("/common/file")
@Api(value = "/common/file",description = "文件管理服务")
public class FileController extends BaseController {

	@Autowired
	private FileUploadService fileUploadServiceImpl;

	@PostMapping("/upload")
	@ApiOperation(value = "上传文件", notes = "上传文件")
	public ResultDto upload(@RequestParam("file") MultipartFile file, HttpServletRequest req) throws Exception {
		String fileName = fileUploadServiceImpl.uploadFile(file);
		return resultSuccess(fileName);
	}
}
