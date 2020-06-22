package com.longing.dubbo.ext.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.fastjson.JSON;

public class OrgFilter implements Filter{
	
	
	private static Logger logger  = LoggerFactory.getLogger(OrgFilter.class);
	
	
	public Result invoke(Invoker<?> invoker, Invocation invocation)
			throws RpcException {
		
		Object[] args = invocation.getArguments();
		logger.info("Ö´ÐÐ¹ýÂËÆ÷filter===={}",JSON.toJSON(args));
		Result result = invoker.invoke(invocation);
		return result;
	}

}
