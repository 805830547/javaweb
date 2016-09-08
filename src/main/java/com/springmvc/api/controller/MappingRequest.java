package com.springmvc.api.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MappingRequest {

    @RequestMapping(value = "/test")
    public String getCardInfo(HttpServletRequest request, HttpSession session, Model model) {
        return "/success";
    }
}
