package com.spring.resource.aop.aspect.test;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * 测试annotation（注解）方式来定义aop
 * @author wangcanpei
 *
 */
@Aspect
public class HelloWorldAspect2 {

	/**
	 * 定义切入点
	 * @param param
	 */
//	@Pointcut(value="execution(* com.spring.resource.aop..*.sayAdvisorBefore(..)) && args(param)", argNames = "param")
//	public void beforePointcut(String param) {}
	
	/**
	 * 定义通知
	 * @param param
	 */
	@Before(value = "execution(* com.spring.resource.aop..*.sayAdvisorBefore(..)) && args(param)", argNames = "param")
	public void beforeAdvice(JoinPoint jp,String param) {
		Object[] args=jp.getArgs();
		
		System.out.println("===========before advice param:" + param);
	}

	/**
	 * 	value：指定切入点表达式或命名切入点；
			pointcut：同样是指定切入点表达式或命名切入点，如果指定了将覆盖value属性指定的，pointcut具有高优先级；
			argNames：与Schema方式配置中的同义；
			returning：与Schema方式配置中的同义。

	 * @param retVal
	 */
	@AfterReturning(value="execution(* com.spring.resource.aop..*.sayBefore(..))",pointcut="execution(* com.spring.resource.aop..*.sayAfterReturning(..))",argNames="retVal", returning="retVal")
	public void afterReturningAdvice(Object retVal) {
		System.out.println("===========after returning advice retVal:" + retVal);
	}
	
	
	/**
	 * 	value：指定切入点表达式或命名切入点；
	pointcut：同样是指定切入点表达式或命名切入点，如果指定了将覆盖value属性指定的，pointcut具有高优先级；
	argNames：与Schema方式配置中的同义；
	throwing：与Schema方式配置中的同义。

	 * @param exception
	 */
	@AfterThrowing(value="execution(* com.spring.resource.aop..*.sayAfterThrowing(..))",argNames="exception", throwing="exception")
	public void afterThrowingAdvice(Exception exception) {
		System.out.println("===========after throwing advice exception:" + exception);
	}


	/**
	 * 	value：指定切入点表达式或命名切入点；
	argNames：与Schema方式配置中的同义；

	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	@Around(value="execution(* com.spring.resource.aop..*.sayAround(..))")
	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
	    System.out.println("===========around before advice");
	    Object retVal = pjp.proceed(new Object[] {"replace"});
	    System.out.println("===========around after advice");
	    return retVal;
	}
	
	/**
	 * value：指定切入点表达式或命名切入点；
	 * argNames：与Schema方式配置中的同义；
	 */
	@After(value="execution(* com.spring.resource.aop..*.sayAfterFinally(..))")
	public void afterFinallyAdvice() {
	    System.out.println("===========after finally advice");
	}



}
