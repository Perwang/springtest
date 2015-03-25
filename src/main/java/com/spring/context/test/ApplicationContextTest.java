package com.spring.context.test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 验证spring的ApplicationContext
 * 
 * spring读取xml文件的各种方法
 * @author wangcanpei
 *
 */
public class ApplicationContextTest {
	
	public static void main(String[] args) {
		//ApplicationContext context=new ClassPathXmlApplicationContext("context.xml");
		//HelloApiImpl helloApi = context.getBean("hello", HelloApiImpl.class);
		//HelloApiImpl helloApi =(HelloApiImpl) context.getBean("hello");
		//HelloApiImpl helloApi = context.getBean(HelloApiImpl.class);
		//helloApi.sayHello();
		//ApplicationContext context=new ClassPathXmlApplicationContext("context.xml");//classPath读取数据
		//WebApplicationContextUtils
		
		
		//ApplicationContext context=new FileSystemXmlApplicationContext("src\\main\\resources\\context.xml");//可以是相对路径（相对于该项目的路径），和绝对路径，
//		System.out.println(context.containsBean("hello")); 
//		System.out.println(context.containsBean("hello1")); 
		
		xmlBeanFactoryRead();
		//classPathXmlApplicationContextRead();
		//fileSystemXmlApplicationContextRead();
		//beanFactoryRead();
	}
	
	/**
	 * 通过xmlBeanFactory来读取xml
	 */
	private static void xmlBeanFactoryRead(){
		
		Resource resource = new ClassPathResource("context.xml");
		BeanFactory factory = new XmlBeanFactory(resource); 
		System.out.println(factory.containsBean("hello"));
		System.out.println(factory.containsBean("hello1"));
	}
	
	
	/**
	 * 从编译路径中读取配置文件
	 */
	private static void classPathXmlApplicationContextRead(){
//		ApplicationContext context=new ClassPathXmlApplicationContext("classpath:context.xml");//classPath读取数据
//		System.out.println(context.containsBean("hello")); 
//		System.out.println(context.containsBean("hello1")); 
		
		// src目录下的
		//ApplicationContext context=new ClassPathXmlApplicationContext("context.xml");
//		ApplicationContext context=new ClassPathXmlApplicationContext("context.xml","context1.xml");
//		System.out.println(context.containsBean("hello")); 
//		System.out.println(context.containsBean("hello1")); 
		// src/conf 目录下的
		//ApplicationContext context=new ClassPathXmlApplicationContext("classpath:conf/contextConf.xml");
		ApplicationContext context=new ClassPathXmlApplicationContext("file:E:/work/dev/workerspace/springTest/target/classes/conf/contextConf.xml"); //通过加file可以当场FileSystemXmlApplicationContext来读取
		System.out.println(context.containsBean("hello")); 
		System.out.println(context.containsBean("hello1")); 
		
	}
	
	/**
	 * 使用文件系统逻辑读取
	 */
	public static void fileSystemXmlApplicationContextRead(){
		//ApplicationContext context=new FileSystemXmlApplicationContext("src\\main\\resources\\context.xml");//可以是相对路径（相对于该项目的路径），和绝对路径，
		
		//使用了  classpath:  前缀,作为标志,  这样,FileSystemXmlApplicationContext 也能够读入classpath下的相对路径
//		ApplicationContext context=new FileSystemXmlApplicationContext("classpath:context.xml");
		
//		ApplicationContext context=new FileSystemXmlApplicationContext("file:E:/work/dev/workerspace/springTest/target/classes/conf/contextConf.xml");
		ApplicationContext context=new FileSystemXmlApplicationContext("E:/work/dev/workerspace/springTest/target/classes/conf/contextConf.xml"); 
		
		System.out.println(context.containsBean("hello")); 
		System.out.println(context.containsBean("hello1")); 
		
	}
	
	
	/**
	 * 这个需要web的容器来支持,因为没有构造函数，所以没有办法给他传入配置文件
	 */
	public static void xmlWebApplicationContextRead(){
		//ServletContext servletContext = request.getSession().getServletContext();
		//ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext ); 
	}
	
	/**
	 * 
	 */
	public static void beanFactoryRead(){
		BeanDefinitionRegistry reg = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(reg);
		reader.loadBeanDefinitions(new ClassPathResource("conf/contextConf.xml"));
		BeanFactory context=(BeanFactory)reg; 
		System.out.println(context.containsBean("hello")); 
		System.out.println(context.containsBean("hello1")); 
	}
	
}
