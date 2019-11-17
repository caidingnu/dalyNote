package com.example.mail.controller;

import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.util.List;
import java.util.Properties;

/**
 * 发送文字邮箱
 */

public class MailUtils {

    /**
     * 邮件属性可以放在 MimeMessageHelper中，也可以放在MimeMessage中
     *
     * @param sendEmailAddress 发送邮箱号
     * @param receiveEmailAddress 接受的邮箱数组
     * @param title 标题
     * @param text 文本
     * @param authCode 授权码
     * @param attachments 附件
     * @throws Exception
     */
    public static String sendMail(String sendEmailAddress, String[] receiveEmailAddress, String title, String text, String authCode, List<File> attachments) throws Exception {
        //设置发送邮件的主机  smtp.qq.com
        String host = "smtp.qq.com";
        //1.创建连接对象，连接到邮箱服务器
        Properties props = System.getProperties();
        //Properties 用来设置服务器地址，主机名 。。 可以省略
//设置邮件服务器
        props.setProperty("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        //SSL加密
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);
        //props：用来设置服务器地址，主机名；Authenticator：认证信息
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            //通过密码认证信息
            protected PasswordAuthentication getPasswordAuthentication() {
                //new PasswordAuthentication(用户名, password);
                //这个用户名密码就可以登录到邮箱服务器了,用它给别人发送邮件
                return new PasswordAuthentication(sendEmailAddress, authCode);
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            //2.1设置发件人：
            helper.setFrom(sendEmailAddress);
//            收件人
            helper.setTo(receiveEmailAddress);
//            helper.setSubject(title); // 标题
//            message.setFrom(new InternetAddress(sendEmailAddress));
            //2.3邮件的主题
            message.setSubject(title);
            Multipart multipart = new MimeMultipart();
//            //邮件正文
            BodyPart contentPart = new MimeBodyPart();
            //2.4设置邮件的正文 第一个参数是邮件的正文内容 第二个参数是：是文本还是html的连接
            contentPart.setContent(text, "text/html;charset=utf-8");
            multipart.addBodyPart(contentPart);
            //邮件附件
            if (attachments != null && attachments.size()>0) {
                for (File attachment : attachments) {
                    BodyPart attachmentPart = new MimeBodyPart();
                    DataSource source = new FileDataSource(attachment);
                    attachmentPart.setDataHandler(new DataHandler(source));
                    //避免中文乱码的处理
                    attachmentPart.setFileName(MimeUtility.encodeWord(attachment.getName()));
                    multipart.addBodyPart(attachmentPart);
                }
            }
            message.setContent(multipart);
            //保存邮件
            message.saveChanges();
            Transport.send(message);
            return "邮件发送成功";
        } catch (MessagingException mex) {
            mex.printStackTrace();
            return "发送失败:" + mex.getMessage();
        }

    }


}