package com.spring.resource.aop.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopTest {

	public static void main(String[] args) {
		//testHelloworld();
		//testSchemaBeforeAdvice();
		//testSchemaAfterReturningAdvice();
		
		//testSchemaAfterThrowingAdvice();
		
		testSchemaAroundAdvice();
	}

	public static void testHelloworld() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"helloworldAop.xml");
		IHelloWorldService helloworldService = ctx.getBean("helloWorldService",
				IHelloWorldService.class);
		helloworldService.sayHello();
	}

	
	
	public static void testSchemaBeforeAdvice(){
		
		     ApplicationContext ctx = 
		new ClassPathXmlApplicationContext("helloworldAop.xml");
		     System.out.println("======================================");
		     IHelloWorldService helloworldService = 
		ctx.getBean("helloWorldService", IHelloWorldService.class);
		
		   System.out.println("======================================");
		   helloworldService.sayBefore("before","wangcanpei");
		}
	
	
	public static void testSchemaAfterReturningAdvice() {
		   System.out.println("======================================");
		   ApplicationContext ctx = new ClassPathXmlApplicationContext("helloworldAop.xml");
		   IHelloWorldService helloworldService = ctx.getBean("helloWorldService", IHelloWorldService.class);
		   boolean flag=helloworldService.sayAfterReturning();
		   System.out.println("======================================"+flag );
		   
		}

	public static void testSchemaAfterThrowingAdvice() {
	    System.out.println("======================================");
	ApplicationContext ctx = 
	new ClassPathXmlApplicationContext("helloworldAop.xml");
	IHelloWorldService helloworldService = 
	ctx.getBean("helloWorldService", IHelloWorldService.class);
	helloworldService.sayAfterThrowing();    
	System.out.println("======================================");
	}
	
	
	public static void testSchemaAroundAdvice() {
		System.out.println("======================================");
		ApplicationContext ctx = 
		new ClassPathXmlApplicationContext("helloworldAop.xml");
		IHelloWorldService helloworldService = 
		ctx.getBean("helloWorldService", IHelloWorldService.class);
		  String p=  helloworldService.sayAround("haha");   
		    
		   System.out.println("======================================"+p);
	}


}
