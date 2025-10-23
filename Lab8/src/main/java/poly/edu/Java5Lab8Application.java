package poly.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

// Lớp chính khởi động ứng dụng Spring Boot
@SpringBootApplication // Tự động cấu hình Spring Boot
@EnableScheduling // Bật chức năng lập lịch (@Scheduled) cho việc gửi email hàng đợi
public class Java5Lab8Application {
    // Phương thức main khởi động ứng dụng
    public static void main(String[] args) {
        SpringApplication.run(Java5Lab8Application.class, args);
    }
}
