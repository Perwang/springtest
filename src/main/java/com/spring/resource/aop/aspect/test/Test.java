package com.spring.resource.aop.aspect.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.resource.aop.test.IHelloWorldService;

public class Test {
	public static void main(String[] args) {
		testAnnotationBeforeAdvice();
	}
	
	public  static void testAnnotationBeforeAdvice() {
		System.out.println("======================================");
		ApplicationContext ctx = 
		new ClassPathXmlApplicationContext("helloworldAopAspect.xml");
		IHelloWorldService helloworldService = 
		ctx.getBean("helloWorldService", IHelloWorldService.class);
		helloworldService.sayAdvisorBefore("before");    
		System.out.println("======================================");
		}

}
