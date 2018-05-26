package RssNotifier.core;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger;

@Aspect
public class RssCheckerLogger {
	@Autowired
	Logger coreLogger;

	@Pointcut("execution(* RssNotifier.core.ScheduledRssChecker.checkRss())")
	public void rssCheck() {
	}

	@Before("rssCheck()")
	public void logRssCheck() {
		coreLogger.info("Checking rss...");
	}
}
