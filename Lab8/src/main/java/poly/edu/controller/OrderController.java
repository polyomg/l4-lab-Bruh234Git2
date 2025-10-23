package poly.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

    @GetMapping("/order/list")
    public String list() {
        return "order/list";
    }
}
