package com.spring.resource.test;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.util.Assert;

public class ResourceLoadTest {
	public static void main(String[] args) {
		new ResourceLoadTest().testResourceLoad();
	}
	
	public void testResourceLoad() {
	    ResourceLoader loader = new DefaultResourceLoader();
	    Resource resource = loader.getResource("classpath:context.xml");
	//验证返回的是ClassPathResource
	Assert.isAssignable(ClassPathResource.class, resource.getClass());
	Resource resource2 = loader.getResource("file:context.xml");
	    //验证返回的是ClassPathResource
	//Assert.isAssignable(FileSystemResource.class, resource2.getClass()); 不会返回FileSystemResource
	Assert.isAssignable(UrlResource.class, resource2.getClass());
	    Resource resource3 = loader.getResource("context.xml");
	    //验证返默认可以加载ClasspathResource
	    Assert.isAssignable(ClassPathResource.class,resource3.getClass());
	   
	}

}
