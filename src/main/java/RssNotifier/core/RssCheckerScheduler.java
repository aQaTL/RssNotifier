package RssNotifier.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.FixedRateTask;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.logging.Logger;

public class RssCheckerScheduler {
	private RssChecker rssChecker;
	private long rate;

	private ScheduledTaskRegistrar taskRegistrar;

	private ScheduledTask scheduledTask;

	public RssCheckerScheduler(RssChecker rssChecker, long rate) {
		this.rssChecker = rssChecker;
		this.rate = rate;
	}

	private void updateRssTask() {
		if (taskRegistrar == null) {
			return;
		}
		if (scheduledTask != null) {
			scheduledTask.cancel();
		}
		FixedRateTask checkRssTask = new FixedRateTask(this::checkRss, rate, 0);
		scheduledTask = taskRegistrar.scheduleFixedRateTask(checkRssTask);
	}

	@Autowired
	Logger coreLogger;

	public void checkRss() {
		coreLogger.info("PERFORMING RSS CHECK");
	}

	public long getRate() {
		return rate;
	}

	public void setRate(long rate) {
		this.rate = rate;
		updateRssTask();
	}

	public void setTaskRegistrar(ScheduledTaskRegistrar taskRegistrar) {
		this.taskRegistrar = taskRegistrar;
		updateRssTask();
	}
}
