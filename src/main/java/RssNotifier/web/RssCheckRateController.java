package RssNotifier.web;

import RssNotifier.core.RssCheckerScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.logging.Logger;

@Controller
@RequestMapping(path = {"/changeRate"})
public class RssCheckRateController {
	@Autowired
	RssCheckerScheduler rssCheckerScheduler;

	@Autowired
	Logger coreLogger;

	@RequestMapping(method = RequestMethod.POST)
	public String changeRate(@RequestParam(name = "rate", required = true) String rate, Model model) {
		long newRate;
		try {
			newRate = Long.parseLong(rate);
		} catch (Exception e) {
			coreLogger.severe(e.getMessage());
			return getRate(model);
		}

		coreLogger.info(String.format("new rate %d", newRate));
		rssCheckerScheduler.setRate(newRate);

		model.addAttribute("rate", newRate);
		return "rss_check_rate";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getRate(Model model) {
		model.addAttribute("rate", rssCheckerScheduler.getRate());
		return "rss_check_rate";
	}
}
