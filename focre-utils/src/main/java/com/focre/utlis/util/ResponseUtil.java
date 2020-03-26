package com.focre.utlis.util;

import com.focre.utlis.jackson.JacksonUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponseUtil {

	/**
	 * @description [渲染json对象]
	 * @title renderJson
	 * @author ye21st
	 * @date 2020/3/23
	 * @time 4:05 下午
	 * @param response []
	 * @param jsonObject []
	 **/
	public static void renderJson(HttpServletResponse response, Object jsonObject) {
		PrintWriter out = null;
		try {
			response.reset();
			// 跨域访问CORS
			response.addHeader("Access-Control-Allow-Origin", "*");
			response.addHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,PUT,DELETE,HEAD");
			response.addHeader("Access-Control-Allow-Headers", "content-type");
			response.addHeader("Access-Control-Max-Age", "36000");
			response.addHeader("Access-Control-Allow-Credentials", "true");

			// 让请求，不被缓存，
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Cache-Control", "no-store");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("application/json; charset=utf-8");
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			out.write(JacksonUtil.obj2json(jsonObject));
			response.flushBuffer();
		} catch (IOException e) {

		} finally {
			if (null != out) {
				out.flush();
				out.close();
			}
		}
	}
}
