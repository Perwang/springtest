package com.spring.resource.match.test;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.Assert;

/**
 * 注意：用classpath*:需要遍历所有的classpath，所以加载速度是很慢的，因此，在规划的时候，应该尽可能规划好资源文件所在的路径，尽量避免使用classpath*。 
 * @author wangcanpei
 *
 */
public class ResourceMatchTest {

	public static void main(String[] args) {
		try {
			//testClasspathPrefix();
			testClasspathAsteriskPrefix();
			testClasspathAsteriskPrefixLimit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void testClasspathPrefix() throws IOException {
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		  //只加载一个绝对匹配Resource，且通过ResourceLoader.getResource进行加载
		  Resource[] resources=resolver.getResources("classpath:META-INF/INDEX.LIST");
		  Assert.isTrue(resources.length==1);
		  //只加载一个匹配的Resource，且通过ResourceLoader.getResource进行加载
		  resources = resolver.getResources("classpath:META-INF/*.LIST");
		  Assert.isTrue(resources.length == 1);   
		  
		}
	
	
	public static void testClasspathAsteriskPrefix () throws IOException {
		 ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();       
		 //将加载多个绝对匹配的所有Resource
		 //将首先通过ClassLoader.getResources("META-INF")加载非模式路径部分
		 //然后进行遍历模式匹配
		Resource[] resources=resolver.getResources("classpath*:META-INF/INDEX.LIST");
		 Assert.isTrue(resources.length > 1);     
		//将加载多个模式匹配的Resource
		 resources = resolver.getResources("classpath*:META-INF/*.LIST");
		  Assert.isTrue(resources.length > 1);   
		}
	
	
	/**
	 * 如果想加载jar包里的文件，必须使用非模式的方式，或者非模式开头的文件夹下面包含模式的，是因为ClassLoader.getResources("")加载目录的原因
	 * 因此在通过前缀“classpath*”加载通配符路径时，必须包含一个根目录才能保证加载的资源是所有的，而不是部分。
	 * @throws IOException
	 */
	public static void testClasspathAsteriskPrefixLimit() throws IOException {
		  ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();      //将首先通过ClassLoader.getResources("")加载目录， 
		//将只返回文件系统的类路径不返回jar的跟路径
		//然后进行遍历模式匹配
		  Resource[] resources = resolver.getResources("classpath*:asm-*.txt");
		Assert.isTrue(resources.length == 0);
		//将通过ClassLoader.getResources("asm-license.txt")加载
		  //asm-license.txt存在于com.springsource.net.sf.cglib-2.2.0.jar
		  resources = resolver.getResources("classpath*:asm-license.txt");
		 // resources = resolver.getResources("classpath*:*-license.txt");
		  Assert.isTrue(resources.length > 0);      
		//将只加载文件系统类路径匹配的Resource
		//  resources = resolver.getResources("classpath*:LICENS*");
		  resources = resolver.getResources("classpath*:*ENSE");
		  Assert.isTrue(resources.length == 1);
		}



}
