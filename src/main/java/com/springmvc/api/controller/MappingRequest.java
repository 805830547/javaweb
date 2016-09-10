package com.springmvc.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MappingRequest {

    @RequestMapping(value = "/test")
    public String getCardInfo(HttpServletRequest request) {
        return "/success";
    }

    @RequestMapping(value = "/card")
    public String RedictHtml(HttpServletRequest request) {
        return "/cardHolder";
    }
}
