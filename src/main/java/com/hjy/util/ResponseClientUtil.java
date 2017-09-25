package com.hjy.util;

import com.alibaba.fastjson.JSONObject;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;

public class ResponseClientUtil {
	private static String CONTENT_TYPE = "text/html;charset=utf-8";

	public static void toClient(HttpServletResponse response, Object object)
			throws IOException {
		PrintWriter out = response.getWriter();
		out.print(object);
		out.flush();
		out.close();
	}

	public static void toClient(HttpServletResponse response, String content)
			throws IOException {
		response.setContentType(CONTENT_TYPE);
		response.getWriter().print(content);
		response.getWriter().flush();
		response.getWriter().close();
	}

	public static void toClient(HttpServletResponse response,
			HashMap<String, String> map) throws IOException {
		if (null != map)
			toClient(response, jsonObject(map));
	}

	private static String jsonObject(HashMap<String, String> map) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.putAll(map);
		return jsonObj.toString();
	}

	public static void toClient(HttpServletResponse response, byte[] image)
			throws IOException {
		BufferedImage imag = null;
		imag = ImageIO.read(new ByteArrayInputStream(image));
		OutputStream out = response.getOutputStream();
		ImageIO.write(imag, "png", out);
	}

}
