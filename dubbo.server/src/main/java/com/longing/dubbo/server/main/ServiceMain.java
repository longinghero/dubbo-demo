package com.longing.dubbo.server.main;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceMain {
	
	public static void main(String[] args) throws IOException{
		
		ClassPathXmlApplicationContext  context = new ClassPathXmlApplicationContext("application.xml");
		System.out.println("Æô¶¯======");
        System.in.read();  

		
	}
}
