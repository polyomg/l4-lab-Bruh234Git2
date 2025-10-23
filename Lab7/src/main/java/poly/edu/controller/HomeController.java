package poly.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * HomeController - Controller hiển thị trang chủ
 * Trang chủ chứa các link đến các bài tập trong Lab7
 */
@Controller
public class HomeController {
	
	/**
	 * Hiển thị trang chủ
	 * URL: / hoặc /home
	 * 
	 * @return Tên view trang chủ
	 */
	@RequestMapping({"/", "/home"})
	public String index() {
		return "index";
	}
}
