package com.spring.resource.test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.Assert;

/**
 * 对Spring的resource进行测试
 * 
 * @author wangcanpei
 * 
 */
public class ResourceTest {
	public static void main(String[] args) {
		// testByteArrayResource();
		// testInputStreamResource();
		//testFileResource();
		try {
			//testClasspathResourceByDefaultClassLoader();
			//testClasspathResourceByClassLoader();
			//testClasspathResourceByClass();
			new ResourceTest().classpathResourceTestFromURL();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 对FileResource的测试
	 */
	public static void testFileResource() {
		File file = new File("d:/pms.log.2014-09-18");
		Resource resource = new FileSystemResource(file);
		if (resource.exists()) {
			dumpStream(resource);
		}
		Assert.isTrue(!resource.isOpen());
	}

	/**
	 * 对inputStreamResource的测试
	 *  InputStreamResource代表java.io.InputStream字节流，对于“getInputStream ”操作将直接返回该字节流，因此只能读取一次该字节流，即“isOpen”永远返回true。
	 */
	public static void testInputStreamResource() {
		ByteArrayInputStream bis = new ByteArrayInputStream(
				"Hello World!".getBytes());
		Resource resource = new InputStreamResource(bis);
		if (resource.exists()) {
			dumpStream(resource);
		}
		// Assert.assertEquals(true, );
		Assert.isTrue(resource.isOpen());// InputStreamResource代表java.io.InputStream字节流，对于“getInputStream
											// ”操作将直接返回该字节流，因此只能读取一次该字节流，即“isOpen”永远返回true。
	}

	/**
	 * 测试byteArrayResource
	 */
	public static void testByteArrayResource() {
		Resource resource = new ByteArrayResource("Hello World!".getBytes());
		if (resource.exists()) {
			dumpStream(resource);
		}
		Assert.isTrue(!resource.isOpen());// ByteArrayResource可多次读取数组资源，即isOpen
											// ()永远返回false。
	}

	/**
	 * 默认的加载器加载资源
	 * 
	 * @throws IOException
	 */
	public static void testClasspathResourceByDefaultClassLoader()
			throws IOException {
		Resource resource = new ClassPathResource(
				"testByDefaultClassLoader.xml");
		if (resource.exists()) {
			dumpStream(resource);
		}
		System.out.println("path:" + resource.getFile().getAbsolutePath());
		Assert.isTrue(!resource.isOpen());
	}

	/**
	 * 使用指定的ClassLoader进行加载资源，将加载指定的ClassLoader类路径上相对于根路径的资源
	 * @throws IOException
	 */
	public static void testClasspathResourceByClassLoader() throws IOException {
		ClassLoader cl = ResourceTest.class.getClassLoader();
		//Resource resource = new ClassPathResource("testByClassLoader.xml", cl);
		Resource resource = new ClassPathResource(
				"com/spring/resource/test/testByClassLoader.xml", cl);
		if (resource.exists()) {
			dumpStream(resource);
		}
		System.out.println("path:" + resource.getFile().getAbsolutePath());
		Assert.isTrue(!resource.isOpen());
	}
	
	/**
	 * 使用指定的类进行加载资源，将尝试加载相对于当前类的路径的资源
	 * 这里的路径是相对于类的class的路径
	 * @throws IOException
	 */
	public  static void testClasspathResourceByClass() throws IOException {
		  // Class clazz = this.getClass();
		Resource resource1 = 
		new ClassPathResource("test/testClasspathResourceByClass.xml" , ResourceTest.class);
		    if(resource1.exists()) {
		        dumpStream(resource1);
		    }
		    System.out.println("path:" + resource1.getFile().getAbsolutePath());
		    Assert.isTrue(!resource1.isOpen());
		        
		    Resource resource2 = new ClassPathResource("testClasspathResourceByClass.xml" , ResourceTest.class);
		    if(resource2.exists()) {
		        dumpStream(resource2);
		   }
		    System.out.println("path:" + resource2.getFile().getAbsolutePath());
		    Assert.isTrue(!resource2.isOpen());
		}


	/**
	 * 加载jar包里的资源，首先在当前类路径下找不到，最后才到Jar包里找，而且在第一个Jar包里找到的将被返回：
	 * @throws IOException
	 */
	public void classpathResourceTestFromJar() throws IOException {
		Resource resource = new ClassPathResource ("http://ofc.shop.letv.com/download/FocusIssued.xls");
		    if(resource.exists()) {
		        dumpStream(resource);
		    }
		    System.out.println("path:" + resource.getURL().getPath());
		    Assert.isTrue(resource.isOpen());
		}
	
	/**
	 * 从URL中加载文件
	 * @throws IOException
	 */
	public void classpathResourceTestFromURL() throws IOException {
		Resource resource = new UrlResource("http://i.shop.letv.com/download/FocusIssued.xls");
		    if(resource.exists()) {
		        dumpStream(resource);
		    }
		    System.out.println("path:" + resource.getURL().getPath());
		    Assert.isTrue(resource.isOpen());
		}

	private static void dumpStream(Resource resource) {
		InputStream is = null;
		try {
			// 1.获取文件资源
			is = resource.getInputStream();
			// 2.读取资源
			int count = is.available();
			byte[] descBytes = new byte[count];
			is.read(descBytes);
			System.out.println(new String(descBytes));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 3.关闭资源
				is.close();
			} catch (IOException e) {
			}
		}
	}

}
