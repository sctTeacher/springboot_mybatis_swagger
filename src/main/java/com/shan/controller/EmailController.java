package com.shan.controller;

import com.shan.common.mail.MailboxUtil;
import com.shan.common.result.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sc
 * @createTime 2020/1/14 17:51
 * @description
 */
@Api(description = "发邮件管理")
@Slf4j
@RestController
public class EmailController {

    @Autowired
    private MailboxUtil mailboxUtil;

    /**
     * 发送普通文本
     * @return
     */
    @GetMapping("/testSendEmi")
    public String testSendEmi(){
        String sendEmail ="shanconga@163.com";
        String subject="日志";
        String test ="尊敬的用户您好:请查收验证码8888";
       boolean b = mailboxUtil.simpleMailSend(sendEmail, subject, test);
        return b == true ? "发送成功" : "发送失败";
    }

    /**
     * 测试群发  普通文本
     * @return
     */
    @GetMapping("/testSendEmi5")
    public String testSendEmi5(){
        String subject="日志";
        String test ="尊敬的用户您好:请查收验证码8888";
        //测试群发多人多附件
        String [] address = {"shanconga@163.com","846859503@qq.com"};
        boolean b =  mailboxUtil.sendBatchMail(address, subject, test);
        return b == true ? "发送成功" : "发送失败";
    }


    /**
     * 发送附件,支持多附件
     * @return
     */
    @GetMapping("/testSendEmi2")
    public String testSendEmi2(){
        String sendEmail ="shanconga@163.com";
        String subject="日志";
        String test ="尊敬的用户您好:请查收验证码8888";
        Map<String,String> paths =new HashMap<String,String>();
        paths.put("文库分享授权分析.doc", "D:\\文库分享授权分析.doc");
        boolean b = mailboxUtil.attachedSend(sendEmail, subject, test, paths);
        return b == true ? "发送成功" : "发送失败";
    }



    /**
     * 发送附件,支持多附件
     * @return
     */
    @GetMapping("/testSendEmi3")
    public String testSendEmi3(){
        String sendEmail ="shanconga@163.com";
        String subject="日志";
        //测试发送富文本（html文件）
        String text = "<body><p style='color:red;'>尊敬的用户您好:请查收验证码8888</p><img src='cid:file1'/><img src='cid:file2'/></body>";
        Map<String,String> map = new HashMap();
        map.put("file1", "D:\\1.png");
        map.put("file2", "D:\\1.png");
        boolean b = mailboxUtil.richContentSend(sendEmail,subject,text,map);
        return b == true ? "发送成功" : "发送失败";
    }


    /**
     * 测试群发多人多附件
     * @return
     */
    @GetMapping("/testSendEmi4")
    public String testSendEmi4(){
        String subject="日志";
        String test ="尊敬的用户您好:请查收验证码8888";
        //测试群发多人多附件
        String [] address = {"shanconga@163.com","846859503@qq.com"};
        String [] filePath = {"D:\\文库分享授权分析.doc", "D:\\1.png"};
        boolean b =  mailboxUtil.sendBatchMailWithFile(address, subject, test,  filePath);
        return b == true ? "发送成功" : "发送失败";
    }


}
