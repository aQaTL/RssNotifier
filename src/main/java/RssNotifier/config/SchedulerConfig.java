package RssNotifier.config;

import RssNotifier.core.RssChecker;
import RssNotifier.core.RssCheckerScheduler;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.concurrent.TimeUnit;

@Configuration
@ComponentScan(basePackages = "RssNotifier",
		excludeFilters = {
				@ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)
		})
@EnableScheduling
public class SchedulerConfig implements SchedulingConfigurer, ApplicationContextAware {
	private ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setTaskScheduler(poolScheduler());

		RssCheckerScheduler rssCheckerScheduler = applicationContext.getBean(
				"rssCheckerScheduler", RssCheckerScheduler.class);
		rssCheckerScheduler.setTaskRegistrar(taskRegistrar);
	}

	@Bean(name = "rssCheckerScheduler")
	RssCheckerScheduler rssCheckerScheduler(RssChecker rssChecker) {
		long rate = TimeUnit.SECONDS.toMillis(5);
		return new RssCheckerScheduler(rssChecker, rate);
	}

	@Bean
	public TaskScheduler poolScheduler() {
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.setThreadNamePrefix("poolScheduler");
		scheduler.setPoolSize(10);
		return scheduler;
	}
}
