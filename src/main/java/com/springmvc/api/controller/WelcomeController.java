package com.springmvc.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springmvc.api.model.Student;
import com.springmvc.api.persistence.StudentDao;



@Controller
public class WelcomeController {
    @Autowired
    private StudentDao studentDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(WelcomeController.class);

    @RequestMapping(value = "/studentest")
    public String color(HttpServletRequest request) {
        LOGGER.debug("Test DB Insert Start..");

        try {
            List<Student> studentList= studentDao.getStudent();
            for (Student student : studentList) {
                System.out.println(student.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/welcome";
    }

}