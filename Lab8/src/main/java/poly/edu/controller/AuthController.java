package poly.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import poly.edu.entity.Account;
import poly.edu.service.AccountService;

// Controller xử lý xác thực (login/logout)
@Controller
public class AuthController {

    // Inject AccountService để truy xuất thông tin tài khoản
    @Autowired
    AccountService accountService;

    // Inject HttpSession để quản lý phiên đăng nhập
    @Autowired
    HttpSession session;

    // Hiển thị trang đăng nhập
    @GetMapping("/auth/login")
    public String loginForm(Model model) {
        return "/auth/login"; // Trả về view login.html
    }

    // Xử lý đăng nhập khi người dùng submit form
    @PostMapping("/auth/login")
    public String loginProcess(Model model,
                               @RequestParam("username") String username,
                               @RequestParam("password") String password) {

        // Tìm tài khoản theo username
        Account user = accountService.findById(username);

        // Kiểm tra tài khoản và mật khẩu
        if (user == null) {
            // Tài khoản không tồn tại
            model.addAttribute("message", "Invalid email!");
        } else if (!user.getPassword().equals(password)) {
            // Mật khẩu không đúng
            model.addAttribute("message", "Invalid password!");
        } else {
            // Đăng nhập thành công
            session.setAttribute("user", user); // Lưu user vào session
            model.addAttribute("message", "Login successfully!");
            // Lấy URL bảo mật đã lưu trước đó (nếu có)
            String securityUri = (String) session.getAttribute("securityUri");
            if (securityUri != null) {
                session.removeAttribute("securityUri");
                return "redirect:" + securityUri; // Chuyển hướng về trang đã yêu cầu
            }
        }

        return "/auth/login"; // Quay lại trang login
    }

    // Xử lý đăng xuất
    @GetMapping("/auth/logout")
    public String logout() {
        // Xóa thông tin user khỏi session
        session.removeAttribute("user");
        session.removeAttribute("securityUri");
        return "redirect:/auth/login"; // Chuyển hướng về trang login
    }
}
