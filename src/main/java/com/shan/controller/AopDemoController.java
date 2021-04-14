package com.shan.controller;
import com.shan.common.result.Result;
import com.shan.config.aop.DemoLogAn;
import com.shan.service.impl.AopDemoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shanc
 * @version 1.0
 */
@RestController
public class AopDemoController {

    @Autowired
    private AopDemoImpl dmoImpl;
    @RequestMapping("/demo")
    @DemoLogAn("sc")
    public Result test(@RequestParam(value = "age") int age, @RequestParam(value = "name") String name , @RequestParam(value = "sex")int sex){
        System.out.println("執行方法111");
        String bb = dmoImpl.demoApi(age, name, sex);
       //int a=1/0;
        System.out.println(bb);
        return Result.success(bb);
    }

}
