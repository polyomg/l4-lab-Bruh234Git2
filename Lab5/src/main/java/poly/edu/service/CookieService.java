package poly.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CookieService {
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;

	/**
	* Đọc cookie từ request
	* @param name tên cookie cần đọc
	* @return đối tượng cookie đọc được hoặc null nếu không tồn tại
	*/
	public Cookie get(String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equalsIgnoreCase(name)) {
                    return c;
                }
            }
        }
        return null;
    }
	/**
	* Đọc giá trị của cookie từ request
	* @param name tên cookie cần đọc
	* @return chuỗi giá trị đọc được hoặc rỗng nếu không tồn tại
	*/
	public String getValue(String name) {
        Cookie c = get(name);
        return (c != null) ? c.getValue() : "";
    }
	/**
	* Tạo và gửi cookie về client
	* @param name tên cookie
	* @param value giá trị cookie
	* @param hours thời hạn (giờ)
	* @return đối tượng cookie đã tạo
	*/
	public Cookie add(String name, String value, int hours) {
        Cookie c = new Cookie(name, value);
        c.setPath("/");
        c.setMaxAge(hours * 60 * 60);
        response.addCookie(c);
        return c;
    }
	/**
	* Xóa cookie khỏi client
	* @param name tên cookie cần xóa
	*/
	public void remove(String name) {
        Cookie c = new Cookie(name, "");
        c.setPath("/");
        c.setMaxAge(0); // hết hạn ngay lập tức
        response.addCookie(c);
    }}
