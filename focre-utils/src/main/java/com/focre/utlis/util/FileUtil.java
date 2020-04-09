package com.focre.utlis.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * @ClassName: FileUtil
 * @Description: 文件处理类
 * @Author ye21st
 * @Date 2020/4/9 5:08 下午:43
 */
public class FileUtil {

	/**
	 * @description [缓存文件头信息-文件头信息]
	 * @title
	 * @author ye21st
	 * @date 2020/4/9
	 * @time 5:19 下午
	 **/
	public static final HashMap<String, String> mFileTypes = new HashMap<String, String>();


	static {
		// images
		mFileTypes.put("FFD8FF", "jpg");
		mFileTypes.put("89504E47", "png");
		mFileTypes.put("47494638", "gif");
		mFileTypes.put("49492A00", "tif");
		mFileTypes.put("424D", "bmp");
		// CAD
		mFileTypes.put("41433130", "dwg");
		mFileTypes.put("38425053", "psd");
		// 日记本
		mFileTypes.put("7B5C727466", "rtf");
		mFileTypes.put("3C3F786D6C", "xml");
		mFileTypes.put("68746D6C3E", "html");
		// 邮件
		mFileTypes.put("44656C69766572792D646174653A", "eml");
		mFileTypes.put("D0CF11E0", "doc");
		mFileTypes.put("5374616E64617264204A", "mdb");
		mFileTypes.put("252150532D41646F6265", "ps");
		mFileTypes.put("255044462D312E", "pdf");
		mFileTypes.put("504B0304", "docx");
		mFileTypes.put("52617221", "rar");
		mFileTypes.put("57415645", "wav");
		mFileTypes.put("41564920", "avi");
		mFileTypes.put("2E524D46", "rm");
		mFileTypes.put("000001BA", "mpg");
		mFileTypes.put("000001B3", "mpg");
		mFileTypes.put("6D6F6F76", "mov");
		mFileTypes.put("3026B2758E66CF11", "asf");
		mFileTypes.put("4D546864", "mid");
		mFileTypes.put("1F8B08", "gz");
		mFileTypes.put("4D5A9000", "exe/dll");
		mFileTypes.put("75736167", "txt");
	}

	/**
	 * @description [根据文件路径获取文件头信息]
	 * @title getFileType
	 * @author ye21st
	 * @date 2020/4/9
	 * @time 5:24 下午
	 * @param filePath [文件路径]
	 * @return java.lang.String 文件头信息
	 **/
	public static String getFileType(String filePath) throws Exception {
		return mFileTypes.get(getFileHeader(filePath));
	}

	/**
	 * @description [根据文件获取文件头信息]
	 * @title getFileType
	 * @author ye21st
	 * @date 2020/4/9
	 * @time 5:25 下午
	 * @param file [文件属性]
	 * @return java.lang.String 文件头信息
	 **/
	public static String getFileType(MultipartFile file) throws Exception {
		return mFileTypes.get(getFileHeader(file));
	}

	/**
	 * @description [根据文件路径获取文件头信息]
	 * @title getFileHeader
	 * @author ye21st
	 * @date 2020/4/9
	 * @time 5:24 下午
	 * @param filePath [文件路径]
	 * @return java.lang.String 文件头信息
	 **/
	public static String getFileHeader(String filePath) throws Exception {
		FileInputStream is = null;
		String value = null;
		try {
			is = new FileInputStream(filePath);
			byte[] b = new byte[4];
			is.read(b, 0, b.length);
			value = bytesToHexString(b);
		}finally {
			if (null != is) {
				is.close();
			}
		}
		return value;
	}

	/**
	 * @description [根据文件对象获取文件头信息]
	 * @title getFileHeader
	 * @author ye21st
	 * @date 2020/4/9
	 * @time 5:24 下午
	 * @param file [文件对象]
	 * @return java.lang.String 文件头信息
	 **/
	public static String getFileHeader(MultipartFile file) throws Exception {
		InputStream is = null;
		String value = null;
		try {
			is = file.getInputStream();
			byte[] b = new byte[4];
			is.read(b, 0, b.length);
			value = bytesToHexString(b);
		} finally {
			if (null != is) {
				is.close();
			}
		}
		return value;
	}

	private static String bytesToHexString(byte[] src) {
		StringBuilder builder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		String hv;
		for (byte b : src) {
			hv = Integer.toHexString(b & 0xFF).toUpperCase();
			if (hv.length() < 2) {
				builder.append(0);
			}
			builder.append(hv);
		}
		return builder.toString();
	}

	/**
	 * @description [获取当前年月日目录地址]
	 * @title getNowDateString
	 * @author ye21st
	 * @date 2020/4/10
	 * @time 12:01 上午
	 * @return java.lang.String
	 **/
	public static String getNowDateString() {
		return DateUtil.formatDate(LocalDateTime.now(), "/yyyy/MM/dd");
	}
}
