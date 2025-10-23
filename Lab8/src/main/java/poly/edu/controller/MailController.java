package poly.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import poly.edu.service.MailService;

// Controller xử lý các yêu cầu liên quan đến email
@Controller
public class MailController {

    // Inject MailService để sử dụng chức năng gửi email
    @Autowired
    MailService mailService;

    // Endpoint gửi email đơn giản
    @ResponseBody // Trả về kết quả dưới dạng text
    @RequestMapping("/mail/send")
    public String send() {
        try {
            // Gọi email với thông tin cơ bản
            mailService.send("receiver@gmail.com", "Subject", "Body");
            return "Mail của bạn đã được gửi đi!";
        } catch (Exception e) {
            // Bắt lỗi và trả về thông báo lỗi
            return "Gửi mail thất bại: " + e.getMessage();
        }
    }
}
