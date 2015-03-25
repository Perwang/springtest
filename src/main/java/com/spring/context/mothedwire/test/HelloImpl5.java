package com.spring.context.mothedwire.test;

import com.spring.context.autowire.test.HelloApi;

public abstract class HelloImpl5 implements HelloApi {

	private Printer printer;

	public void sayHello() {
		printer.print("setter");
		createSingletonPrinter().print("singleton");
		createPrototypePrinter().print("prototype");
	}

	public abstract Printer createPrototypePrinter();

	public Printer createSingletonPrinter() {
		System.out.println("该方法不会被执行，如果输出就错了");
		return new Printer();
	}

	public void setPrinter(Printer printer) {
		this.printer = printer;
	}

}
