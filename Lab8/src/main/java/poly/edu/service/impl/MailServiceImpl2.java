package poly.edu.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jakarta.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import poly.edu.service.MailService2;

// Service implementation cho việc gửi email với hàng đợi
@Service("mailService2")
public class MailServiceImpl2 implements MailService2 {

    // Inject JavaMailSender để gửi email
    @Autowired
    JavaMailSender mailSender;

    // Danh sách hàng đợi chứa các email chờ gửi
    List<Mail> queue = new ArrayList<>();

    // Phương thức gửi email ngay lập tức
    @Override
    public void send(Mail mail) {
        try {
            // 1. Tạo đối tượng MimeMessage
            MimeMessage message = mailSender.createMimeMessage();
            // 2. Tạo helper hỗ trợ ghi nội dung (multipart, utf-8)
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

            // 2.1. Cấu hình tin người gửi
            helper.setFrom(mail.getFrom()); // Địa chỉ người gửi
            helper.setReplyTo(mail.getFrom()); // Địa chỉ reply-to

            // 2.2. Ghi thông tin người nhận
            helper.setTo(mail.getTo()); // Người nhận chính
            if (!isNullOrEmpty(mail.getCc())) helper.setCc(mail.getCc()); // CC nếu có
            if (!isNullOrEmpty(mail.getBcc())) helper.setBcc(mail.getBcc()); // BCC nếu có

            // 2.3. Ghi tiêu đề và nội dung
            helper.setSubject(mail.getSubject()); // Tiêu đề email
            helper.setText(mail.getBody(), true); // Nội dung (true = hỗ trợ HTML)

            // 2.4. Đính kèm file (nếu có)
            String filenames = mail.getFilenames();
            if (!isNullOrEmpty(filenames)) {
                // Tách chuỗi filenames theo dấu phẩy hoặc chấm phẩy
                for (String filename : filenames.split("[,;]+")) {
                    File file = new File(filename.trim());
                    // Chỉ đính kèm nếu file tồn tại
                    if (file.exists()) {
                        helper.addAttachment(file.getName(), file);
                    }
                }
            }

            // 3. Gửi Mail
            mailSender.send(message);
            System.out.println("Gửi mail thành công đến: " + mail.getTo());

        } catch (Exception e) {
            // In lỗi ra console
            e.printStackTrace();
        }
    }

    // Phương thức kiểm tra chuỗi null hoặc rỗng
    private boolean isNullOrEmpty(String text) {
        return (text == null || text.trim().isEmpty());
    }

    // Phương thức thêm email vào hàng đợi (không gửi ngay)
    @Override
    public void push(Mail mail) {
        // Thêm email vào danh sách queue
        queue.add(mail);
        System.out.println("Đã thêm mail vào hàng đợi: " + mail.getTo());
    }

    // Phương thức tự động gửi email từ hàng đợi mỗi 0.5 giây
    @Scheduled(fixedDelay = 500) // Chạy sau mỗi 0.5 giây
    public void run() {
        // Lặp qua hàng đợi và gửi từng email
        while (!queue.isEmpty()) {
            try {
                // Lấy email đầu tiên trong queue và gửi đi
                this.send(queue.remove(0));
            } catch (Exception e) {
                // In lỗi nếu có
                e.printStackTrace();
            }
        }
    }
}
