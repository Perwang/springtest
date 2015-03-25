package com.spring.context.methodReplacer.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.context.mothedwire.test.Printer;

public class Test {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("methodReplacerInject.xml");
				 Printer printer = context.getBean("printer", Printer.class);
				 printer.print("我将被替换");

	}
}
