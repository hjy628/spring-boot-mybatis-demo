package com.hjy.conf.datasource;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * Created by hjy on 17-9-25.
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/druid/*",
        initParams={
                @WebInitParam(name="allow",value=""),// IP白名单 (没有配置或者为空，则允许所有访问)
                @WebInitParam(name="loginUsername",value="adminHjyTest"),// 用户名
                @WebInitParam(name="loginPassword",value="hjy!321WxTest"),// 密码
                @WebInitParam(name="resetEnable",value="false")// 禁用HTML页面上的“Reset All”功能
        })
public class DruidStateViewConfig extends StatViewServlet {
}
