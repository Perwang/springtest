package com.spring.resource.aop.test;

import org.aspectj.lang.ProceedingJoinPoint;

public class HelloWorldAspect {
	// 前置通知
	public void beforeAdvice() {
		System.out.println("===========before advice");
	}
	// 后置最终通知
	public void afterFinallyAdvice() {
		System.out.println("===========after finally advice");
	}
	public void beforeAdvice(String param,String name) {
		System.out.println("===========before advice param:" + param+"name="+name);
	}
	public boolean afterReturningAdvice(Object dd) {
	    System.out.println("===========after returning advice retVal:" + dd);
	    dd=false;
	    return false;
	}
	
	public void afterThrowingAdvice(Exception exception) {
		  System.out.println("===========after throwing advice exception: " + exception.getMessage());
	}

	/**
	 * 不带参数的处理
	 */
//	public void afterThrowingAdvice() {
//		  System.out.println("===========after throwing advice exception:" );
//	}

	
	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
	    System.out.println("===========around before advice");
	    Object retVal = pjp.proceed(new Object[] {"replace"});
	System.out.println("===========around after advice");
	    return retVal;
	}

}
