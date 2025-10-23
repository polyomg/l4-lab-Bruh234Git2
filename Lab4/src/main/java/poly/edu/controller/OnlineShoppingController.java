package poly.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OnlineShoppingController {
	@RequestMapping("/home/index")
    public String index(Model model) {
        return "index";
    }

    @RequestMapping("/home/about")
    public String about(Model model) {
        return "about"; 
    }
}
