package com.spring.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Slf4j
@Aspect
@Component
public class MethodExecutionCalculationAspect {

    @Pointcut("execution(* com.spring.demo.controllers.*.*(..))")
    public void webLayerExecution() {}

    @Pointcut("execution(* com.spring.demo.daos.*.*(..))")
    public void doaLayerExecution() {}

    @Pointcut("execution(* com.spring.demo.services.*.*(..))")
    public void serviceLayerExecution() {}

    @Pointcut("webLayerExecution() || doaLayerExecution() || serviceLayerExecution()")
    public void forAllAppExecution() {}

    @Pointcut("@annotation(com.spring.demo.aop.TrackTime)")
    public void annotationTrackTime() {}

    @Around("annotationTrackTime()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        final Object proceed = joinPoint.proceed();
        long timeTaken = System.currentTimeMillis() - startTime;
        log.info("Time Taken by {} is {}", joinPoint, timeTaken);
        return proceed;
    }

    @Before("forAllAppExecution()")
    public void before(JoinPoint theJoinPoint) {
        String theMethod = theJoinPoint.getSignature().toShortString();
        log.info("=====>> in @Before: calling method: " + theMethod);

        Object[] args = theJoinPoint.getArgs();
        Stream.of(args).forEach(tempArg -> log.info("=====>> argument: " + tempArg));
    }

    @AfterReturning(
            pointcut="forAllAppExecution()",
            returning="result"
    )
    public void afterReturning(JoinPoint theJoinPoint, Object result) {
        String theMethod = theJoinPoint.getSignature().toShortString();
        log.info("=====>> in @AfterReturning: from method: " + result);
        log.info("=====>> result: " + result);
    }
}
