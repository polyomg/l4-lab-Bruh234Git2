package poly.edu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Cấu hình đăng ký các Interceptor vào Spring MVC
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    // Inject AuthInterceptor để đăng ký
    @Autowired
    AuthInterceptor authInterceptor;

    // Phương thức đăng ký các Interceptor
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Đăng ký AuthInterceptor cho các đường dẫn cần bảo mật
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/admin/**",           // Tất cả trang admin
                        "/account/change-password",      // Trang đổi mật khẩu
                        "/account/edit-profile",         // Trang sửa thông tin
                        "/order/**")                     // Tất cả trang đơn hàng
                .excludePathPatterns("/admin/home/index"); // Loại trừ trang admin/home/index
    }
}
