package com.fzy.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ProductAop {
    @AfterReturning(value = "execution(public * com.fzy.service.impl.PmsProductServiceImpl.changeStatus(..))")
    public void updateSearch(JoinPoint joinPoint){
Object[] args = joinPoint.getArgs();
String id=String.valueOf(args[0]);
Boolean isPublish=Boolean.valueOf(String.valueOf(args[1]));
        System.out.println("------------------------------");
        System.out.println("id:"+id);
        System.out.println("isPublish:"+isPublish);
    }
}
