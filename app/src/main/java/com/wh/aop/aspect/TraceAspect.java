package com.wh.aop.aspect;

import android.util.Log;

import com.wh.aop.service.DebugLog;
import com.wh.aop.service.StopWatch;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Created by wanghao on 2017/4/19.
 */
@Aspect
public class TraceAspect {

    private static final String POINTCUT_METHOD =
            "execution(@com.wh.aop.annotation.DebugTrace * *(..))";

    private static final String POINTCUT_CONSTRUCTOR =
            "execution(@com.wh.aop.annotation.DebugTrace *.new(..))";

    @Pointcut(POINTCUT_METHOD)
    public void methodAnnotatedWithDebugTrace() {}

    @Pointcut(POINTCUT_CONSTRUCTOR)
    public void constructorAnnotatedDebugTrace() {}

    //@Around("methodAnnotatedWithDebugTrace() || constructorAnnotatedDebugTrace()")
    public Object weaveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        Log.i("WANGHAO","类名:"+className+"函数名:"+methodName+"执行前");
        /*final StopWatch stopWatch = new StopWatch();
        stopWatch.start();*/
        Object result = joinPoint.proceed();
        //stopWatch.stop();
        Log.i("WANGHAO","类名:"+className+"函数名:"+methodName+"执行后");
        //DebugLog.log(className, buildLogMessage(methodName, stopWatch.getTotalTimeMillis()));

        return result;
    }

    @Before("execution(@com.wh.aop.annotation.DebugTrace * *(..))")
    public void weaveJoinPointBefore(JoinPoint joinPoint) throws Throwable{
        String key = joinPoint.getSignature().toString();
        Log.i("WANGHAO","执行前--------------key"+key);
    }

    /**
     * Create a log message.
     *
     * @param methodName A string with the method name.
     * @param methodDuration Duration of the method in milliseconds.
     * @return A string representing message.
     */
    private static String buildLogMessage(String methodName, long methodDuration) {
        StringBuilder message = new StringBuilder();
        message.append("wanghao --> ");
        message.append(methodName);
        message.append(" --> ");
        message.append("[");
        message.append(methodDuration);
        message.append("ms");
        message.append("]");

        return message.toString();
    }
}
