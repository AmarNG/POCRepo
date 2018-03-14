package com.example.config;

import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LoggingAspect {
	
	private static final Logger logger = (Logger) LoggerFactory.getLogger(LoggingAspect.class);
	
	 public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
			StopWatch stopWatch = new StopWatch();
			stopWatch.start();
	        long start = System.currentTimeMillis();
	        ((org.slf4j.Logger) logger).debug(">>>>>>>>>>" + pjp.getSignature().toShortString() + " START >>>>>>>>>>>>");
	        ((org.slf4j.Logger) logger).debug("entering "+pjp.getStaticPart().getSignature().toString());
	        Object retVal = pjp.proceed();
	        long end = System.currentTimeMillis();
	        stopWatch.stop();
	        ((org.slf4j.Logger) logger).debug("[" + pjp.getSignature().toShortString() + "] method Execution Time: " + (end - start) + " ms.");
	        ((org.slf4j.Logger) logger).debug("[" + pjp.getSignature().toShortString() + "] method Execution Time using stop watch: " + stopWatch.getTotalTimeMillis() + " ms.");
	        ((org.slf4j.Logger) logger).debug( "<<<<<<<<" + pjp.getSignature().toShortString() + " END <<<<<<<<");
	        return retVal;
	    }

}
