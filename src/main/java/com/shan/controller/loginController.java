package com.shan.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.shan.domain.User;
import com.shan.mapper.UserMapper;
import com.shan.utils.R;
import com.shan.utils.ShiroUtils;
import io.swagger.annotations.*;
import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Api(description = "登录管理")
@Controller
public class loginController {

    @Autowired
    private Producer producer;

    /**
     * 去登陆页面
     * @return
     */
    @ApiOperation(value = "去登录", notes = "去登录")
    @RequestMapping(value = "/toLogin", method = RequestMethod.GET)
    public String toLogin() {
      //  System.out.println("去登陆");
        return "login";
    }

    /**
     * 获取验证码
     * @param request
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "获得验证码", notes = "获得验证码")
    @GetMapping("/getCaptchaCode")
    public void captcha(HttpServletRequest request, HttpServletResponse response)throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //保存到shiro session
       ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);
       Object sessionAttribute = ShiroUtils.getSessionAttribute(Constants.KAPTCHA_SESSION_KEY);

       // session.setAttribute(Constants.KAPTCHA_SESSION_KEY, text);
        //Object sessionAttribute = session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        System.out.println("获得验证码中的验证码"+sessionAttribute);
        System.out.println( "获得验证码sessionId:" +ShiroUtils.getSession().getId());

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
    }



    /**
     * 登录
     * @return
     */
    @ApiOperation(value = "登录", notes = "登录")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public R login( @RequestParam("username") String username ,
                     @RequestParam("password") String password ,
                     @RequestParam("captchaCode") String captchaCode,
                     HttpServletRequest request) {
      //  HttpSession session = request.getSession();
        //Object sessionAttribute = session.getAttribute(Constants.KAPTCHA_SESSION_KEY);

       Object sessionAttribute = ShiroUtils.getSessionAttribute(Constants.KAPTCHA_SESSION_KEY);
        System.out.println("登陆session中存的验证码"+sessionAttribute);
        System.out.println( "登陆中sessionId:" +ShiroUtils.getSession().getId());


        String code = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
        if(!captchaCode.equalsIgnoreCase(sessionAttribute.toString())){
            return R.error("验证码不正确");
        }
        try {
            Subject subject = ShiroUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            subject.login(token);
        } catch (UnknownAccountException e) {
            return R.error("验证码不正确");
        }
        return R.ok(username);
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        System.out.println("去index");
        return "index";
    }

    @RequestMapping(value = "/unauthorized", method = RequestMethod.GET)
    public String unauthorized() {
        System.out.println("去unauthorized");
        return "unauthorized";
    }
}
