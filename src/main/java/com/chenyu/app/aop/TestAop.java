package com.chenyu.app.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: chenyu
 * @CreateTime: 2022-07-19
 * @Description: aop
 */
@Aspect
@Component
public class TestAop {
    @Pointcut("execution (* com.chenyu.app.service.IPersonService.testTransactional(*))")
    public void test() {

    }

    @AfterReturning(returning = "data", pointcut = "TestAop.test()")
    public void afterAdvice(JoinPoint jp, Object data) {
        String[] parameterNames = ((MethodSignature) jp.getSignature()).getParameterNames();
        Map<String, Object> param = new HashMap<>();

        Object[] args = jp.getArgs();
        for (int i = 0; i < parameterNames.length; i++) {
            param.put(parameterNames[i], args[i]);
        }

        param.forEach((k, v) -> System.out.println(k + ":" + v));
    }
}
