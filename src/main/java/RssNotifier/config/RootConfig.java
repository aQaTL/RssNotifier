package RssNotifier.config;

import RssNotifier.core.RssChecker;
import RssNotifier.core.RssCheckerLogger;
import RssNotifier.core.ScheduledRssChecker;
import RssNotifier.data.RssRepository;
import org.mockito.Mockito;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.logging.Logger;

@Configuration
@ComponentScan(basePackages = "RssNotifier",
		excludeFilters = {
				@Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)
		})
@EnableScheduling
@EnableAspectJAutoProxy
public class RootConfig {

	@Bean
	RssChecker rssChecker() {
		return new RssChecker(rssRepository());
	}

	@Bean
	RssRepository rssRepository() {
		return Mockito.mock(RssRepository.class);
	}

	@Bean
	ScheduledRssChecker scheduledRssChecker(RssChecker rssChecker) {
		return new ScheduledRssChecker(rssChecker);
	}

	@Bean
	RssCheckerLogger rssCheckerLogger() {
		return new RssCheckerLogger();
	}
}
