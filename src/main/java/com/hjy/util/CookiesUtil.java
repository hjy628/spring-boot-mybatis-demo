package com.hjy.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by hjy on 17-9-25.
 */
public class CookiesUtil {

    /**
     * 添加cookie
     *
     * @param cookieName
     * @param data
     * @param expires
     * @param response
     */
    public static void addCookie(String cookieName, String data, int expires, HttpServletResponse response) throws UnsupportedEncodingException {
        Cookie cookie = new Cookie(cookieName, URLEncoder.encode(data, "utf-8"));
        cookie.setMaxAge(expires);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * 删除cookie
     *
     * @param cookieName
     * @param response
     */
    public static void delCookie(String cookieName, HttpServletResponse response) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * 获取cookie
     * @param cookieName
     * @param request
     * @return
     */
    public static String getCookieValue(String cookieName, HttpServletRequest request) throws UnsupportedEncodingException {
        Cookie[] cookies = request.getCookies();
        String vlaue = null;
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie c = cookies[i];
                if (c.getName().equalsIgnoreCase(cookieName)) {
                    vlaue =URLDecoder.decode(c.getValue(),"utf-8");
                    break;
                }
            }
        }
        return vlaue;
    }
}
