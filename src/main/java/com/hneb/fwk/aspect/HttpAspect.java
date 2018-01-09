package com.hneb.fwk.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
public class HttpAspect {

	private final static Logger log = LoggerFactory.getLogger(HttpAspect.class);

	
	//在com.hneb 下的所有类之前执行doBefre 之后执行doAfter
	@Pointcut("execution(public * com.hneb.*(..))")
	public void logs(){
		
	}
	
	
	@Before("logs()")
	public void doBefore(JoinPoint point){
		log.info("do before");
		ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		//url
		log.info("url={}",request.getRequestURL());
		
		//method
		log.info("method={}",request.getMethod());
		
		//ip
		log.info("ip={}",request.getRemoteAddr());
		
		//类方法
		log.info("class_method={}",point.getSignature().getDeclaringTypeName()+"."+point.getSignature().getName());
		
		//参数
		log.info("args={}",point.getArgs());
		
		
	}
	
	@After("logs()")
	public void doAfter(){
		log.info("do after");
	}
	
	//在doAfter之后执行，主要用户记录程序执行后的返回值
	@AfterReturning(returning="object",pointcut="logs()")
	public void doAfterReturning(Object object){
		log.info("response={}",object.toString());
	}
}
