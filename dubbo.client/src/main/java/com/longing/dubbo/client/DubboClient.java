package com.longing.dubbo.client;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.longing.dubbo.server.DemoService;

public class DubboClient {
	
	public static void main(String[] args) throws IOException{
		
		ClassPathXmlApplicationContext  context = new ClassPathXmlApplicationContext("application-client.xml");
		
		DemoService demoService = (DemoService) context.getBean("demoService");

		System.out.println(demoService.sayHello("dubbo"));
		System.in.read();
	}
}
