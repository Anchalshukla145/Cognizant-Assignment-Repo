package com.library;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.library.BookService.*(..))")
    public void logBefore() {
        System.out.println("[Aspect Log] Before method execution: Preparing to invoke service method.");
    }

    @After("execution(* com.library.BookService.*(..))")
    public void logAfter() {
        System.out.println("[Aspect Log] After method execution: Service method invocation completed.");
    }

    @Around("execution(* com.library.BookService.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("[Aspect Log] Method " + joinPoint.getSignature().getName() + " executed in " + executionTime + " ms.");
        return result;
    }
}
