package RssNotifier.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.logging.Logger;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping(path = {"/", "/home", "index"})
public class HomeController {
	@Autowired
	Logger requestLogger;

	@RequestMapping(method = GET)
	public String index(@RequestParam(name = "name", defaultValue = "anon", required = false) String name, Model model) {
		requestLogger.info(String.format("Request param name: \"%s\"", name));
		model.addAttribute("name", name);
		return "index";
	}
}
