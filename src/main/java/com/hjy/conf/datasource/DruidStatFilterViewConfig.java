package com.hjy.conf.datasource;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * Created by hjy on 17-9-25.
 */
@WebFilter(filterName = "druidWebStatFilter", urlPatterns = "/*",
initParams = {
        @WebInitParam(name = "exclusions", value = "*.html,*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")// 忽略资源
})
public class DruidStatFilterViewConfig extends WebStatFilter {
}
