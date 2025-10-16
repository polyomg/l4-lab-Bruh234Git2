package poly.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import poly.edu.service.ParamService;
import poly.edu.service.SessionService;
import poly.edu.service.CookieService;

import java.io.File;

@Controller

public class AccountController {
	
	@Autowired
	CookieService cookieService;
	@Autowired
	ParamService paramService;
	@Autowired
	SessionService sessionService;
	
	@GetMapping("/account/login")
	public String login1() {
		return "/account/login";
	}

	@PostMapping("/account/login")
	public String login2() {
	    String un = paramService.getString("username", "");
	    String pw = paramService.getString("password", "");
	    boolean rm = paramService.getBoolean("remember", false);

	    if (un.equals("poly") && pw.equals("123")) {
	    	sessionService.set("username", un);
	        if (rm) {
	            cookieService.add("user", un, 10);
	        } else {
	            cookieService.remove("user");
	        }
	        return "redirect:/home/index";
	    }
	    return "/account/login";
	}

	@GetMapping("/account/register")
    public String register1() {
        return "/account/register";
    }

    @PostMapping("/account/register")
    public String register2(MultipartFile photo) {
        String un = paramService.getString("username", "");
        String pw = paramService.getString("password", "");

        File savedFile = paramService.save(photo, "/images");

        if (savedFile != null) {
            System.out.println("Đăng ký thành công! Ảnh lưu tại: " + savedFile.getAbsolutePath());
        } else {
            System.out.println("Chưa chọn ảnh hoặc lưu thất bại!");
        }
        return "/account/login";
    }
}
