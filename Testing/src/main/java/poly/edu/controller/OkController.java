package poly.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OkController {
    @RequestMapping("/poly/ok")
    public String ok() {
        return "/poly/ok";
    }

    @PostMapping("/ctrl/ok")
    public String m1(Model model) {
        model.addAttribute("methodName", "m1");
        return "/poly/ok";
    }

    @GetMapping("/ctrl/ok")
    public String m2(Model model) {
        model.addAttribute("methodName", "m2");
        return "/poly/ok";
    }
    
    @PostMapping("/ctrl/ok/{x}")
    public String m3(Model model, @PathVariable("x") Integer x) {
        model.addAttribute("methodName", "m3" + x);
        return "/poly/ok";
    }
}