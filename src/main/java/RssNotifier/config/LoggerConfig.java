package RssNotifier.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.logging.*;

@Configuration
@ComponentScan(basePackages = "RssNotifier",
		excludeFilters = {
				@ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)
		})
public class LoggerConfig {
	@Bean
	Logger requestLogger() {
		Logger logger = Logger.getLogger("REQUEST_LOGGER");
		logger.setLevel(Level.FINE);
		return logger;
	}

	@Bean
	Logger coreLogger() {
		Logger logger = Logger.getLogger("CORE_LOGGER");
		logger.setLevel(Level.FINE);
		for (Handler handler : logger.getHandlers()) {
			logger.removeHandler(handler);
		}
		logger.addHandler(new StreamHandler(System.out, new SimpleFormatter()));
		return logger;
	}
}
