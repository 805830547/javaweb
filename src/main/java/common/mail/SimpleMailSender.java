package common.mail;

import java.util.Date;
import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Administrator
 *
 */
public class SimpleMailSender {
    /**
     * Mail发送
     *
     * @param mailInfo
     * @return
     */
    public boolean sendHtmlMail(MailSenderInfo mailInfo) {

        // 判断是否需要身份验证
        MyAuthenticator authenticator = null;
        Properties pro = mailInfo.getProperties();

        // 如果需要身份验证，则创建一个密码验证器
        authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());

        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
        // sendMailSession.setDebug(true);
        // 创建邮件发送者地址
        try {
            // 根据session创建一个邮件信息
            Message mailMessage = new MimeMessage(sendMailSession);
            Address from = new InternetAddress(mailInfo.getFromAddress());
            // 设置邮件消息的发送者
            mailMessage.setFrom(from);
            // 设置接受者地址
            Address to = new InternetAddress(mailInfo.getToAddress());
            mailMessage.setRecipient(Message.RecipientType.TO, to);
            // 设置邮件消息的主题
            mailMessage.setSubject(mailInfo.getSubject());
            // 设置发送时间
            mailMessage.setSentDate(new Date());
            // Multipart是一个容器类，包含了MimeBodyPart
            Multipart mainPart = new MimeMultipart();
            // 创建一个包含html内容的mimeBodyPary
            BodyPart html = new MimeBodyPart();

            html.setContent(mailInfo.getContent(), "text/html;charset=utf-8");
            mainPart.addBodyPart(html);
            // 发送邮件
            MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
            mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
            mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
            mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
            mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
            mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
            CommandMap.setDefaultCommandMap(mc);
            mailMessage.setContent(mainPart);

            Transport.send(mailMessage);

            return true;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
}
