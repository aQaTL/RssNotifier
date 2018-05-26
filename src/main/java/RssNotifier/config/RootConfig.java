package RssNotifier.config;

import RssNotifier.RssChecker;
import RssNotifier.data.RssRepository;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "RssNotifier",
		excludeFilters = {
				@Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)
		})
public class RootConfig {

	@Bean
	RssChecker rssChecker() {
		return new RssChecker(rssRepository());
	}

	@Bean
	RssRepository rssRepository() {
		return Mockito.mock(RssRepository.class);
	}
}
