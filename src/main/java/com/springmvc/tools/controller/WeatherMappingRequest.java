package com.springmvc.tools.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springmvc.myutil.jexcelsql.CityService;

import jxl.Sheet;
import jxl.Workbook;

@Controller
public class WeatherMappingRequest {

    private final String WEATHER_API_KEY = "dk3xfwvps4bnz7tg";
    private final String WEATHER_API_ENDPOINT = "https://api.thinkpage.cn/v3/weather/now.json";

    @RequestMapping(value = "/weather", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getCardInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IllegalStateException, IOException {
        // String url =
        // "https://api.thinkpage.cn/v3/weather/now.json?key=dk3xfwvps4bnz7tg&location=beijing&language=zh-Hans&unit=c";

        DefaultHttpClient httpclient = new DefaultHttpClient();

        String cityId = CityService.getCityIdString("city_id",
                "city_name_zh='" + request.getParameter("location") + "'");
        // 目标地址
        String urlString = createWeatherAPIURL(cityId, request.getParameter("language"), request.getParameter("unit"));
        HttpGet httpGet = new HttpGet(urlString);

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
        StringBuffer responseString = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            responseString.append(line);
        }
        if (entity != null) {
            entity.consumeContent();
        }
        // System.out.println(responseString.toString());
        return responseString.toString();
    }

    private String createWeatherAPIURL(String location, String language, String unit) {
        StringBuilder url = new StringBuilder();
        url.append(WEATHER_API_ENDPOINT);
        url.append("?key=");
        url.append(WEATHER_API_KEY);
        url.append("&location=");
        url.append(location);
        url.append("&languag=");
        url.append(language);
        url.append("&unit=");
        url.append(unit);

        return url.toString();
    }

    public static void main(String[] args) throws ClientProtocolException, IOException {
        try {
            Workbook rwb = Workbook.getWorkbook(new File("D:/ss.xls"));
            Sheet rs = rwb.getSheet("city");// 或者rwb.getSheet(0)
            int clos = rs.getColumns();// 得到所有的列
            int rows = rs.getRows();// 得到所有的行

            System.out.println(clos + " rows:" + rows);
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    // 第一个是列数，第二个是行数
                    String id = rs.getCell(j, i).getContents();// 默认最左边编号也算一列
                    System.out.print(id + " ");
                }
                System.out.println();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
