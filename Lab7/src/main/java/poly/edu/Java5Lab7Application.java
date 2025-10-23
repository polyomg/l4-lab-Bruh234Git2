package poly.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Application class cho Lab7 - JpaRepository 2
 * @SpringBootApplication: Annotation tổng hợp bao gồm:
 * - @Configuration: Đánh dấu class này là nguồn cấu hình
 * - @EnableAutoConfiguration: Tự động cấu hình Spring dựa trên dependencies
 * - @ComponentScan: Tự động scan và đăng ký các component trong package
 */
@SpringBootApplication
public class Java5Lab7Application {

	public static void main(String[] args) {
		// Khởi chạy ứng dụng Spring Boot
		SpringApplication.run(Java5Lab7Application.class, args);
	}

}
