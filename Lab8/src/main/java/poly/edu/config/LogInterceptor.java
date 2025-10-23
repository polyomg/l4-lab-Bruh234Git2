package poly.edu.config;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import poly.edu.entity.Account;

// Interceptor ghi log truy cập các trang bảo mật
@Component
public class LogInterceptor implements HandlerInterceptor {

    // Khởi tạo logger để ghi log
    private static final Logger log = LoggerFactory.getLogger(LogInterceptor.class);

    // Phương thức chạy trước khi request được xử lý
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // Lấy session (không tạo mới nếu chưa có)
        HttpSession session = request.getSession(false);
        Account user = null;
        
        // Lấy thông tin user từ session nếu có
        if (session != null) {
            Object u = session.getAttribute("user");
            if (u instanceof Account) user = (Account) u;
        }
        
        // Lấy URI đang truy cập
        String uri = request.getRequestURI();
        // Xác định người truy cập (user hoặc anonymous)
        String who = (user != null) ? user.getFullname() + " (" + user.getUsername() + ")" : "anonymous";
        // Lấy thời gian hiện tại
        LocalDateTime now = LocalDateTime.now();
        
        // Ghi log thông tin truy cập
        log.info("[SECURED PAGE ACCESS] uri='{}', time='{}', user='{}'", uri, now, who);
        
        // Cho phép request tiếp tục
        return true;
    }
}
