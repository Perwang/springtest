package com.spring.context.autowire.test;

public class HelloApiImpl implements HelloApi{
	
	/**
	 * 
	 */
	private HelloApi helloApi;
	
	public void sayHello() {
		System.out.println("Hello World 1!");
		helloApi.sayHello();
	}

	public HelloApi getHelloApi() {
		return helloApi;
	}

	public void setHelloApi(HelloApi helloApi) {
		this.helloApi = helloApi;
	}
	
	
}
