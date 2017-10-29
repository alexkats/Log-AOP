package com.katsman.solanteq.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author Alexey Katsman
 * @since 22.10.17
 */

@Component
@Aspect
public class LoggingAnnotatedMethodsAspect {

    @Around("execution(public * (@com.katsman.solanteq.aspect.Log *).*(..))" +
        "&& !execution(java.lang.String *.toString()) && !execution(int *.hashCode())" +
        "&& !execution(boolean *.canEqual(Object))" +
        "&& !execution(boolean *.equals(Object))")
    public Object logInAndOutMethodClassWrapper(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        Object result;

        if (method.isAnnotationPresent(Log.class)) {
            result = joinPoint.proceed();
        } else {
            result = doLog(joinPoint);
        }

        return result;
    }

    @Around("@annotation(com.katsman.solanteq.aspect.Log) && execution(* *(..))")
    public Object logInAndOutMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        return doLog(joinPoint);
    }

    private Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Logger logger = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
        long resultTime = System.currentTimeMillis();
        logger.debug("Method {} has started", methodName);
        Object result = joinPoint.proceed(joinPoint.getArgs());
        resultTime = System.currentTimeMillis() - resultTime;
        logger.debug("Method {} has finished in {} seconds", methodName, resultTime / 1000.0);

        return result;
    }
}
