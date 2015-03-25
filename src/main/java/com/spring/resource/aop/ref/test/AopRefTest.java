package com.spring.resource.aop.ref.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.resource.aop.test.IHelloWorldService;



public class AopRefTest {
	
	public static void main(String[] args) {
		testSchemaIntroduction();
	}
	
	public static void testSchemaIntroduction() {
	    System.out.println("======================================");
	    ApplicationContext ctx = new ClassPathXmlApplicationContext("helloworldAopRef.xml");
	    IHelloWorldService introductionService = ctx.getBean("helloWorldService", IHelloWorldService.class);
		//introductionService.induct();  
	    Method[] methods=introductionService.getClass().getDeclaredMethods();
	    try {
			Method method= introductionService.getClass().getMethod("induct");
			method.invoke(introductionService);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    System.out.println(methods.length);
		System.out.println("======================================");
	}

}
