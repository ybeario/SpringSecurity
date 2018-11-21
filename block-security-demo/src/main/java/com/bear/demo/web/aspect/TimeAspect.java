package com.bear.demo.web.aspect;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author Y.bear
 * @version 创建时间：2018年11月21日 上午10:32:58 类说明
 */
@Aspect
@Component
public class TimeAspect {
	@Around("execution(* com.bear.demo.web.controller.*.*(..))")
	public Object handleControllerMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		System.out.println("time aspect start");
		Object[] args = proceedingJoinPoint.getArgs();
		System.out.println("Aspect:" + proceedingJoinPoint.getSignature().getDeclaringType());
		System.out.println("Aspect:" + proceedingJoinPoint.getSignature().getName());
		for (Object object : args) {
			System.out.println("arg is: " + object);
		}
		long start = new Date().getTime();
		Object object = proceedingJoinPoint.proceed();
		System.out.println("time aspect 耗时：" + (new Date().getTime() - start));
		System.out.println("time aspect end");
		return object;

	}

}
