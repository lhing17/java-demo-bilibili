package com.example.demo.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class DataSourceAspect {


    @Pointcut("@annotation(com.example.demo.datasource.UsingDataSource)")
    public void checkPointCut() {

    }

    @Before("checkPointCut()")
    public void checkBefore(JoinPoint joinPoint) {

        try {
            Class<?> clazz = joinPoint.getTarget().getClass();
            String methodName = joinPoint.getSignature().getName();
            Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterTypes();
            Method method = clazz.getMethod(methodName, parameterTypes);
            UsingDataSource usingDataSource = method.getAnnotation(UsingDataSource.class);
            String dataSourceKey = usingDataSource.value();
            DataSourceContextHolder.setKey(dataSourceKey);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    @After("checkPointCut()")
    public void checkAfter(){
        DataSourceContextHolder.clearKey();
    }
}
