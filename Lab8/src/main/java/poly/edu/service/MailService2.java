package poly.edu.service;

import lombok.Builder;
import lombok.Data;
import lombok.Builder.Default;

// Interface MailService2 hỗ trợ gửi email trực tiếp và qua hàng đợi
public interface MailService2 {

    // Inner class Mail chứa thông tin email
    @Data
    @Builder
    public static class Mail {
        @Default
        private String from = "WebShop <web-shop@gmail.com>"; // Email người gửi mặc định
        private String to, cc, bcc, subject, body, filenames; // Các thuộc tính email
    }

    // Phương thức gửi email ngay lập tức
    void send(Mail mail);

    // Overload: Gửi email ngay với tham số đơn giản
    default void send(String to, String subject, String body) {
        // Tạo đối tượng Mail và gọi ngay
        Mail mail = Mail.builder()
                .to(to)
                .subject(subject)
                .body(body)
                .build();
        this.send(mail);
    }

    // Phương thục thêm email vào hàng đợi (không gửi ngay)
    void push(Mail mail);

    // Overload: Thêm email vào hàng đợi với tham số đơn giản
    default void push(String to, String subject, String body) {
        // Tạo đối tượng Mail và thêm vào hàng đợi
        this.push(Mail.builder()
                .to(to)
                .subject(subject)
                .body(body)
                .build());
    }
}
