package com.shan.service.impl;

import org.springframework.stereotype.Service;

/**
 * @author shanc
 * @version 1.0
 */
@Service
public class AopDemoImpl {

    public String demoApi(int pam1,String pam2 ,int pam3){
        System.out.println("执行业务逻辑方法add");
        return pam1+pam2+pam3;
    }

}
