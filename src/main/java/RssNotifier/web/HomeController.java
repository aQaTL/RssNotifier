package RssNotifier.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping(path = {"/", "/home", "index"})
public class HomeController {

	@RequestMapping(method = GET)
	public String index(Model model) {
		return "index";
	}
}
