package com.longing.dubbo.context;

public class OrganizationContextHolder {
	
	private  OrganizationContextHolder(){
		
	}
	
	private static ThreadLocal<String> org = new ThreadLocal<String>();
	
	private static ThreadLocal<String> userNo = new ThreadLocal<String>();

	public static String getOrg() {
		
		return OrganizationContextHolder.org.get();
	}

	public static void setOrg(String org) {
		
		OrganizationContextHolder.org.set(org);
	}

	public static String getUserNo(){
		
		return OrganizationContextHolder.userNo.get();
	}
	
	public static void setUserNo(String userNo){
		
		OrganizationContextHolder.userNo.set(userNo);
	}
	
}
