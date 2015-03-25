package com.spring.context.autowire.test;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试跟其他测试完全一样，只是在此我们一定要注册销毁方法回调，否则销毁方法不会执行。
 * @author wangcanpei
 *
 */
public class Test {
	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("autowire/autowire.xml");
		// 一点要注册销毁回调，否则我们定义的销毁方法不执行
		context.registerShutdownHook();
		HelloApi helloApi = context.getBean("bean",HelloApi.class);
		helloApi.sayHello();
	}
}
