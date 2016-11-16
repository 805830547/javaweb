package com.springmvc.test;

import common.mail.MailSenderInfo;
import common.mail.SimpleMailSender;

public class MailTest {

    public static void main(String[] args) {

        MailSenderInfo mailInfo = new MailSenderInfo();
        mailInfo.setMailServerHost("smtp.163.com"); // 主机地址（SMTP简单邮件传输协议）
        mailInfo.setMailServerPort("25");// 主机端口 // 发送者信息
        mailInfo.setUserName("18624350823@163.com");
        mailInfo.setPassword("******");
        mailInfo.setFromAddress("18624350823@163.com"); // 接受者信息
        mailInfo.setToAddress("805830547@qq.com"); // 邮件主题
        mailInfo.setSubject("邮件测试");
        // 邮件内容 mailInfo.
        mailInfo.setContent("君临天下四海为家");

        // 这个类主要发送邮件
        SimpleMailSender sms = new SimpleMailSender();
        sms.sendHtmlMail(mailInfo);
    }

}
