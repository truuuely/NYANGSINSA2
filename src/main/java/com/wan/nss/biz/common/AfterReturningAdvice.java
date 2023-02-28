package com.wan.nss.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class AfterReturningAdvice {
	@AfterReturning(pointcut="PointcutCommon.aPointcut()", returning="obj")
	public void printLogARA(JoinPoint jp, Object obj) {
		System.out.println("[Ctrl() 실행 후 로그]");
		
		System.out.println(jp.getSignature().getName()+"()이 수행 완료되었습니다.");
		System.out.println(jp.getArgs()[0]+"가 인자로 쓰였습니다.");
	
		System.out.println("결과값은 "+obj+" 입니다.");
	}
}