package com.hjy.util;

import org.apache.http.Consts;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by 胡超云 on 2016/11/17.
 */
public class HttpUtil {

    private Logger logger = Logger.getLogger(HttpUtil.class);

    // 默认返回数据的编码格式
    private static Charset DEFAULT_ENCODING_CHARSET = Consts.UTF_8;
    /*
    * 当多线程调用httpclient时，httpclient线程池是必须使用的
    * */
    private static PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();

    static {
        //设置线程池的最大线程数
        connManager.setMaxTotal(100);
        //设置每个路由默认连接数
        connManager.setDefaultMaxPerRoute(5);
    }


    //创建由httpclient线程池管理的客户端
    private static CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connManager).build();

    private static CloseableHttpClient getHttpClient() {
        return httpClient;
    }

    public static String doGet(String url) throws Exception {
        return doGetMain(url, null, null);
    }

    public static String doGet(String url, String encoding) throws Exception {
        return doGetMain(url, null, encoding);
    }

    public static String doGet(String url, Map<String, String> header) throws Exception {
        return doGetMain(url, header, null);
    }

    public static String doGet(String url, Map<String, String> header, String encoding) throws Exception {
        return doGetMain(url, header, encoding);
    }

    /**
     * get请求代理
     *
     * @param url      请求地址
     * @param header   头部信息
     * @param encoding 编码
     * @return response 返回的文本数据
     * @throws Exception 异常信息
     */
    private static String doGetMain(String url, Map<String, String> header, String encoding) throws Exception {
        HttpGet get = new HttpGet(url);
        if (header != null) {
            Set<String> headerNames = header.keySet();
            for (String name : headerNames) {
                get.addHeader(name, header.get(name));
            }
        }
        return httpResult(getHttpClient().execute(get), encoding);
    }

    public static String doPost(String url) throws Exception {
        return doPostMain(url, null, null, null);
    }

    public static String doPost(String url, String encoding) throws Exception {
        return doPostMain(url, null, null, encoding);
    }

    public static String doPostJson(String url, String postData) throws Exception {
        return doPostMain(url, postData, null, null);
    }

    public static String doPostJson(String url, String postData, String encoding) throws Exception {
        return doPostMain(url, postData, null, encoding);
    }

    public static String doPostJson(String url, String postData, Map<String, String> header, String encoding) throws Exception {
        return doPostMain(url, postData, header, encoding);
    }

    /**
     * form表单提交
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public static String doPostForm(String url, List<BasicNameValuePair> params) throws Exception {
        return doPostMainForm(url, params, null, null);
    }

    /**
     * post请求代理
     *
     * @param url      请求的资源ＵＲＬ
     * @param postData POST请求时form表单封装的数据 没有时传null
     * @param header   request请求时附带的头信息(header) 没有时传null
     * @param encoding response返回的信息编码格式 没有时传null
     * @return response 返回的文本数据
     * @throws Exception 异常信息
     */
    private static String doPostMain(String url, String postData, Map<String, String> header, String encoding) throws Exception {
        HttpPost post = new HttpPost(url);

        if (header != null) {
            Set<String> headerNames = header.keySet();
            for (String name : headerNames) {
                post.addHeader(name, header.get(name));
            }
        }
        if (postData != null) {
            StringEntity entity = new StringEntity(postData, DEFAULT_ENCODING_CHARSET);
            post.setEntity(entity);
        }

        return httpResult(getHttpClient().execute(post), encoding);
    }

    /**
     * post请求代理 表单形式
     *
     * @param url      请求的资源ＵＲＬ
     * @param params POST请求时form表单封装的数据 没有时传null
     * @param header   request请求时附带的头信息(header) 没有时传null
     * @param encoding response返回的信息编码格式 没有时传null
     * @return response 返回的文本数据
     * @throws Exception 异常信息
     */
    private static String doPostMainForm(String url, List<BasicNameValuePair> params, Map<String, String> header, String encoding) throws Exception {
        HttpPost post = new HttpPost(url);

        if (header != null) {
            Set<String> headerNames = header.keySet();
            for (String name : headerNames) {
                post.addHeader(name, header.get(name));
            }
        }

        if (params != null) {
            post.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
        }

        return httpResult(getHttpClient().execute(post), encoding);
    }


    /**
     * 处理http请求结果
     *
     * @param response 请求结果
     * @param encoding 结果编码
     * @return 结果
     * @throws Exception 异常信息
     */
    private static String httpResult(CloseableHttpResponse response, String encoding) throws Exception {
        String result = EntityUtils.toString(response.getEntity(), encoding == null ? DEFAULT_ENCODING_CHARSET : Charset.forName(encoding));
        if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            throw new Exception(response.getStatusLine().getStatusCode() + " " + result);
        }
        response.close();
        return result;
    }
}
