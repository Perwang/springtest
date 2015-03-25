package com.spring.navigation.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("navigationBeanInject.xml");
		NavigationA navigationA = context.getBean("a", NavigationA.class);
		navigationA.getNavigationB().getNavigationC().sayNavigation();
		navigationA.getNavigationB().getList().get(0).sayNavigation();
		navigationA.getNavigationB().getMap().get("key").sayNavigation();
		navigationA.getNavigationB().getArray()[0].sayNavigation();
		((NavigationC) navigationA.getNavigationB().getProperties().get("1"))
				.sayNavigation();

	}
}
