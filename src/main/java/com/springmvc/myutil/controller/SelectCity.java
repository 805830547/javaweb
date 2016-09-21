package com.springmvc.myutil.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springmvc.myutil.jexcelsql.CityService;

@Controller
public class SelectCity {

    @RequestMapping(value = "/getProvince", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getProvince(HttpServletRequest request) {
        List<String> list = new ArrayList<String>();
        list = CityService.getStringList("province", "country = " + request.getParameter("country"));
        StringBuffer provinceStr = new StringBuffer();
        for (String lString : list) {
            provinceStr.append(lString);
            provinceStr.append("/");
        }
        return provinceStr.toString();
    }

    @RequestMapping(value = "/getCity", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getCity(HttpServletRequest request) {
        List<String> list = new ArrayList<String>();
        list = CityService.getStringList("city", "province = " + request.getParameter("province"));
        StringBuffer provinceStr = new StringBuffer();
        for (String lString : list) {
            provinceStr.append(lString);
            provinceStr.append("/");
        }
        return provinceStr.toString();
    }

}
