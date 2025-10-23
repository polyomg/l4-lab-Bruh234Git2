package poly.edu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LogInterceptorConfig implements WebMvcConfigurer {

    @Autowired
    LogInterceptor logInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Chỉ áp dụng cho các URI đã bảo vệ giống yêu cầu bài 6
        registry.addInterceptor(logInterceptor)
                .addPathPatterns("/admin/**",
                        "/account/change-password",
                        "/account/edit-profile",
                        "/order/**");
    }
}
