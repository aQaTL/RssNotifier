package RssNotifier.config;

import RssNotifier.core.RssChecker;
import RssNotifier.data.RssRepository;
import org.mockito.Mockito;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "RssNotifier",
		excludeFilters = {
				@Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)
		})
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

}
