package com.springmvc.myutil.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springmvc.myutil.jexcelsql.CityService;

import net.sf.json.JSONObject;

@Controller
public class SelectCity {

    @RequestMapping(value = "/getProvince", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ArrayList<JSONObject> getProvince(HttpServletRequest request) {
        List<String> list = new ArrayList<String>();
        list = CityService.getStringList("province", "country = " + request.getParameter("country"));
        ArrayList<JSONObject> provinceJsonList = new ArrayList<JSONObject>();
        for (String lString : list) {
            JSONObject provinceJson = new JSONObject();
            provinceJson.put("province", lString);
            provinceJsonList.add(provinceJson);
        }
        return provinceJsonList;
    }

    @RequestMapping(value = "/getCity", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ArrayList<JSONObject> getCity(HttpServletRequest request) {
        List<String> list = new ArrayList<String>();
        list = CityService.getStringList("city_name_zh", "province = " + request.getParameter("province"));
        ArrayList<JSONObject> provinceJsonList = new ArrayList<JSONObject>();
        for (String lString : list) {
            JSONObject provinceJson = new JSONObject();
            provinceJson.put("city", lString);
            provinceJsonList.add(provinceJson);
        }
        return provinceJsonList;
    }
}
