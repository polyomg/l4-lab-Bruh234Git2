package poly.edu.service;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

// Interface MailService định nghĩa các phương thức gửi email
public interface MailService {
	// Inner class Mail chứa thông tin email
	@Data // Lombok tự động tạo getter/setter
	@Builder // Lombok tạo builder pattern
	@Accessors(chain = true) // Cho phép gọi method liên tiếp
	public static class Mail {
		@Builder.Default
		private String from = "WebShop <web-shop@gmail.com>"; // Địa chỉ email người gửi mặc định
		private String to; // Địa chỉ email người nhận
		private String cc; // Địa chỉ email CC (carbon copy)
		private String bcc; // Địa chỉ email BCC (blind carbon copy)
		private String subject; // Tiêu đề email
		private String body; // Nội dung email (hỗ trợ HTML)
		private String filenames; // Danh sách file đính kèm (phân cách bởi dấu phẩy)
	}

	// Phương thức gửi email với đối tượng Mail
	void send(Mail mail);

	// Phương thức gửi email đơn giản (overload)
	default void send(String to, String subject, String body) {
		// Tạo đối tượng Mail bằng builder pattern
		Mail mail = Mail.builder().to(to).subject(subject).body(body).build();
		// Gọi phương thức send chính
		this.send(mail);
	}
}
