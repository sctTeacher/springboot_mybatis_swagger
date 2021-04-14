package com.shan.config.aop;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.ExtendedServletRequestDataBinder;

import javax.servlet.http.HttpServletResponseWrapper;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author shanc
 * @version 1.0
 */
@Aspect
@Component
@Slf4j
public class DemoLogAop {

   @Before(value = "@annotation(DemoLogAn)")
   public void  before(JoinPoint joinPoint){
     System.out.println("before");
   }

    @After(value = "@annotation(DemoLogAn)")
    public void  after(JoinPoint joinPoint){
        System.out.println("after");
    }

    @AfterReturning(value = "@annotation(DemoLogAn)")
    public void  afterReturning(JoinPoint joinPoint){
        System.out.println("afterReturning");
    }

    @AfterThrowing(value = "@annotation(DemoLogAn)")
    public void  afterThrowing(JoinPoint joinPoint){
        System.out.println("afterThrowing");
    }

    @Around(value = "@annotation(DemoLogAn)")
    public Object  around(ProceedingJoinPoint pjp){
        Object proceed =null;
        try {
            System.out.println("around before");
            proceed = pjp.proceed();
            System.out.println("around after");
        } catch (Throwable throwable) {
            System.out.println("around Throwing");
            throwable.printStackTrace();
        }
        return proceed;
    }

   /* @Around(value = "@annotation(DemoLogAn)")
    public Object  around(ProceedingJoinPoint pjp){
        Object proceed =null;
        String uuid = UUID.randomUUID().toString().replace("-", "");
        try {
            System.out.println("around before");
            methodBefore(pjp,uuid );
             proceed = pjp.proceed();
            System.out.println("around after");
            methodAfterReturing(proceed,uuid);
        } catch (Throwable throwable) {
            System.out.println("around Throwing");
            throwable.printStackTrace();
        }
        System.out.println("around retun");
        return proceed;
    }
    public void methodBefore(JoinPoint joinPoint, String uuid) {
        // 打印请求内容
        try {
            // 下面两个数组中，参数值和参数名的个数和位置是一一对应的。
            Object[] objs = joinPoint.getArgs();
            String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames(); // 参数名
            Map<String, Object> paramMap = new HashMap<String, Object>();
            for (int i = 0; i < objs.length; i++) {
                if (!(objs[i] instanceof ExtendedServletRequestDataBinder) && !(objs[i] instanceof HttpServletResponseWrapper)) {
                    paramMap.put(argNames[i], objs[i]);
                }
            }
            if (paramMap.size() > 0) {
                log.info("\n[{}]方法:{}\n参数:{}", uuid, joinPoint.getSignature(), JSONObject.toJSONString(paramMap));
            }
        } catch (Exception e) {
            log.error("[{}]AOP methodBefore:", uuid, e);
        }
    }

    public void methodAfterReturing(Object o, String uuid) {
        try {
            if (o != null)
                log.info("[{}]Response内容:{}", uuid, JSONObject.toJSON(o));
        } catch (Exception e) {
            log.error("[{}]AOP methodAfterReturing:", uuid, e);
        }
    }
*/




}
