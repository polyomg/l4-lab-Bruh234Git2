package poly.edu.service;

import java.io.File;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

// Service implementation cho việc gửi email
@Service("mailService")
public class MailServiceImpl implements MailService {

    // Inject JavaMailSender từ Spring Boot
    @Autowired
    JavaMailSender mailSender;

    @Override
    public void send(Mail mail) {
        try {
            // 1. Tạo đối tượng MimeMessage
            MimeMessage message = mailSender.createMimeMessage();

            // 2. Tạo đối tượng hỗ trợ ghi nội dung Mail
            // true = hỗ trợ multipart (đính kèm file), utf-8 = encoding
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

            // 2.1. Ghi thông tin người gửi
            helper.setFrom(mail.getFrom()); // Đặt địa chỉ người gửi
            helper.setReplyTo(mail.getFrom()); // Đặt địa chỉ reply-to

            // 2.2. Ghi thông tin người nhận
            helper.setTo(mail.getTo()); // Đặt người nhận chính
            // Kiểm tra và đặt CC nếu có
            if (!isNullOrEmpty(mail.getCc())) {
                helper.setCc(mail.getCc());
            }
            // Kiểm tra và đặt BCC nếu có
            if (!isNullOrEmpty(mail.getBcc())) {
                helper.setBcc(mail.getBcc());
            }

            // 2.3. Ghi tiêu đề và nội dung
            helper.setSubject(mail.getSubject()); // Đặt tiêu đề email
            helper.setText(mail.getBody(), true); // true = hỗ trợ HTML trong nội dung

            // 2.4. Đính kèm file (nếu có)
            String filenames = mail.getFilenames();
            if (!isNullOrEmpty(filenames)) {
                // Tách chuỗi filenames theo dấu phẩy hoặc chấm phẩy
                for (String filename : filenames.split("[,;]+")) {
                    File file = new File(filename.trim());
                    // Thêm file đính kèm vào email
                    helper.addAttachment(file.getName(), file);
                }
            }

            // 3. Gửi Mail
            mailSender.send(message);

        } catch (Exception e) {
            // Bắt exception và throw RuntimeException
            throw new RuntimeException("Gửi mail thất bại: " + e.getMessage(), e);
        }
    }

    // Phương thức kiểm tra chuỗi null hoặc rỗng
    private boolean isNullOrEmpty(String text) {
        return (text == null || text.trim().isEmpty());
    }
}
