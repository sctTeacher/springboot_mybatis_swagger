package com.shan.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.shan.domain.User;
import com.shan.mapper.UserMapper;
import io.swagger.annotations.*;
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
    @RequestMapping(value = "toLogin", method = RequestMethod.GET)
    public String toLogin() {
        return "login";
    }

    /**
     * 获取验证码
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("getCaptchaCode")
    public void captcha(HttpServletRequest request, HttpServletResponse response)throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        HttpSession session = request.getSession();
        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //保存到shiro session
        //ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, text);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
    }



    /**
     * 登录
     * @return
     */
    @ApiOperation(value = "登录", notes = "登录")
    @PostMapping("/login")
    @ResponseBody
    public List<User> login() {

        return null;
    }




}
