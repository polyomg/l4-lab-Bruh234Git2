package poly.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {

    @GetMapping("/account/edit-profile")
    public String editProfile() {
        return "account/edit-profile";
    }

    @GetMapping("/account/change-password")
    public String changePassword() {
        return "account/change-password";
    }
}
