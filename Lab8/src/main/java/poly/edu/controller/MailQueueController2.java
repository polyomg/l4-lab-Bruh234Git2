package poly.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import poly.edu.service.MailService2;

// Controller xử lý gửi email qua hàng đợi
@Controller
public class MailQueueController2 {

    // Inject MailService2 để sử dụng chức năng gửi email qua hàng đợi
    @Autowired
    MailService2 mailService2;

    // Endpoint thêm email vào hàng đợi
    @ResponseBody // Trả về kết quả dưới dạng text
    @RequestMapping("/mail2/queue")
    public String sendMailQueue() {
        // Thêm email vào hàng đợi (sẽ được gửi tự động bởi @Scheduled)
        mailService2.push("receiver@gmail.com", "Subject", "Body");
        return "Mail của bạn đã được xếp vào hàng đợi!";
    }
}
