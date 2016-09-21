package com.springmvc.tools.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import net.sf.json.JSONObject;

/**
 * @Title:TestHttpClient
 * @Author Tony
 * @Date: 2014年6月21日 下午3:29:37
 * @Description: httpClient使用，1 发送post请求 2 发送get请求
 *
 */
public class TestHttpClient {

    /**
     * @Title: methodPost @Description:
     *         httpclient方法中post提交数据的使用 @param @return @param @throws
     *         Exception @return String @throws
     */
    public static String methodPost() throws Exception {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        // // 代理的设置
        // HttpHost proxy = new HttpHost("10.60.8.20", 8080);
        // httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,
        // proxy);

        // 目标地址
        HttpPost httppost = new HttpPost("http://localhost:8011/testServlet");
        System.out.println("请求: " + httppost.getRequestLine());

        // post 参数 传递
        List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
        nvps.add(new BasicNameValuePair("content", "11111111")); // 参数
        nvps.add(new BasicNameValuePair("path", "D:/file")); // 参数
        nvps.add(new BasicNameValuePair("name", "8")); // 参数
        nvps.add(new BasicNameValuePair("age", "9")); // 参数
        nvps.add(new BasicNameValuePair("username", "wzt")); // 参数

        httppost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8)); // 设置参数给Post

        // 执行
        HttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();
        System.out.println(response.getStatusLine());
        if (entity != null) {
            System.out.println("Response content length: " + entity.getContentLength());
        }
        // 显示结果
        BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));

        String line = null;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        if (entity != null) {
            entity.consumeContent();
        }
        return null;

    }

    /**
     * @Title: methodGet @Description:
     *         以get方法提交数的使用 @param @return @param @throws Exception @return
     *         String @throws
     */
    public static String methodGet() throws Exception {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        // // 代理的设置
        // HttpHost proxy = new HttpHost("10.60.8.20", 8080);
        // httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,
        // proxy);

        // 目标地址
        HttpPost httpGet = new HttpPost("http://localhost:8011/testServlet");

        // 构造最简单的字符串数据
        StringEntity reqEntity = new StringEntity("name=test&password=test");
        // 设置类型
        reqEntity.setContentType("application/x-www-form-urlencoded");
        // 设置请求的数据
        httpGet.setEntity(reqEntity);

        // 执行
        HttpResponse response = httpclient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        System.out.println(response.getStatusLine());

        if (entity != null) {
            System.out.println("Response content length: " + entity.getContentLength()); // 得到返回数据的长度
        }
        // 显示结果
        BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));

        String line = null;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        if (entity != null) {
            entity.consumeContent();
        }
        return null;

    }

    /**
     * 模拟url访问 从特定的url中获取json
     *
     * @param urlStr
     * @param params
     * @return json object ,or null if failed 参数经过封装后传过来 ，提交为 post请求
     */
    private static JSONObject getJsonFromUrl(String urlStr, Map<String, String> params) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(urlStr);
        JSONObject json = null;
        try {
            if (params != null) {
                Iterator<String> keys = params.keySet().iterator();
                List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                while (keys.hasNext()) {
                    String key = keys.next();
                    nvps.add(new BasicNameValuePair(key, params.get(key)));
                }
                httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
            }
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            InputStream is = entity.getContent();
            byte[] bytes = new byte[256];
            StringBuffer sb = new StringBuffer();
            while (is.read(bytes) > 0) {
                sb.append(new String(bytes));
                bytes = new byte[256];
            }
            json = JSONObject.fromObject(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return json;
    }

    /**
     * @Title: main @Description: 测试类 @param @param args @return void @throws
     */
    public static void main(String[] args) {
        try {
            TestHttpClient.methodGet();
            // TestHttpClient.methodPost();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}