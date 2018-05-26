package RssNotifier.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.logging.Logger;

public class ScheduledRssChecker {
	private RssChecker rssChecker;

	@Autowired
	private Logger coreLogger;

	public ScheduledRssChecker(RssChecker rssChecker) {
		this.rssChecker = rssChecker;
	}

	@Scheduled(fixedRate = 5 * 1000)
	void checkRss() {
	}
}
