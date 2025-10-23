package poly.edu.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import poly.edu.entity.Account;

// Interceptor kiểm tra quyền truy cập các trang bảo mật
@Component
public class AuthInterceptor implements HandlerInterceptor {

    // Inject HttpSession để kiểm tra trạng thái đăng nhập
    @Autowired
    HttpSession session;

    // Phương thức chạy trước khi request được xử lý
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // Lấy URI và query string từ request
        String uri = request.getRequestURI();
        String qs = request.getQueryString();
        // Tạo full URI (bao gồm query string nếu có)
        String fullUri = qs == null ? uri : uri + "?" + qs;

        // Lấy thông tin user từ session
        Account user = (Account) session.getAttribute("user");
        
        // Kiểm tra quyền truy cập các trang yêu cầu đăng nhập
        if (uri.startsWith("/account/change-password") ||
            uri.startsWith("/account/edit-profile") ||
            uri.startsWith("/order/")) {
            if (user == null) { // Nếu chưa đăng nhập
                // Lưu URI đang yêu cầu để redirect sau khi login
                session.setAttribute("securityUri", fullUri);
                // Chuyển hướng đến trang login
                response.sendRedirect("/auth/login");
                return false; // Ngăn chặn request
            }
        }

        // Kiểm tra quyền admin cho các trang /admin/**
        if (uri.startsWith("/admin") && !uri.startsWith("/admin/home/index")) {
            if (user == null || !user.isAdmin()) { // Nếu chưa đăng nhập hoặc không phải admin
                // Lưu URI đang yêu cầu
                session.setAttribute("securityUri", fullUri);
                // Chuyển hướng đến trang login
                response.sendRedirect("/auth/login");
                return false; // Ngăn chặn request
            }
        }

        // Cho phép request tiếp tục
        return true;
    }
}
