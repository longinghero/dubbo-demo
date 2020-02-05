package com.longing.dubbo.server.impl;

import com.longing.dubbo.server.DemoService;

public class DemoServiceImpl implements DemoService{

	public String sayHello(String name) {
		// TODO Auto-generated method stub
		return "hello "+name;
	}

}
