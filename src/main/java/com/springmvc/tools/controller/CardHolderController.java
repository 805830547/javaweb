package com.springmvc.tools.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springmvc.tools.model.CardHolder;
import com.springmvc.tools.util.ModelParser;

@Controller
public class CardHolderController {

    @RequestMapping(value = "/cardHolder")
    public String getCardInfo(HttpServletRequest request) {
        CardHolder cardHolder = (CardHolder) ModelParser.parseHttpParametersToServletRequestModel(request,
                new CardHolder());
        System.out.println(cardHolder.getCardNumber());
        System.out.println(cardHolder.getPassWord());
        return "/success";
    }

}
