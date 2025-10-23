package poly.edu.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poly.edu.dao.ProductDAO;
import poly.edu.entity.Product;
import poly.edu.service.SessionService;

/**
 * ProductController - Controller xử lý các request liên quan đến Product
 * Sử dụng @Controller để đánh dấu đây là một Spring MVC Controller
 */
@Controller
public class ProductController {
	
	@Autowired
	ProductDAO dao; // Inject ProductDAO để thao tác với database
	
	@Autowired
	SessionService session; // Inject SessionService để lưu trữ dữ liệu trong session
	
	/**
	 * BÀI 1: Tìm kiếm sản phẩm theo khoảng giá
	 * URL: /product/search
	 * Method: GET hoặc POST
	 * 
	 * @param model Model để truyền dữ liệu sang view
	 * @param min Giá tối thiểu (Optional - có thể null)
	 * @param max Giá tối đa (Optional - có thể null)
	 * @return Tên view để hiển thị
	 */
	@RequestMapping("/product/search")
	public String search(Model model,
			@RequestParam("min") Optional<Double> min,
			@RequestParam("max") Optional<Double> max) {
		
		// Lấy giá trị min, nếu không có thì dùng giá trị nhỏ nhất (Double.MIN_VALUE)
		double minPrice = min.orElse(Double.MIN_VALUE);
		
		// Lấy giá trị max, nếu không có thì dùng giá trị lớn nhất (Double.MAX_VALUE)
		double maxPrice = max.orElse(Double.MAX_VALUE);
		
		// Gọi method findByPrice() từ ProductDAO để tìm sản phẩm theo khoảng giá
		List<Product> items = dao.findByPrice(minPrice, maxPrice);
		
		// Thêm danh sách sản phẩm vào model để hiển thị trên view
		model.addAttribute("items", items);
		
		// Trả về tên view (file HTML trong thư mục templates)
		return "product/search";
	}
	
	/**
	 * BÀI 2: Tìm kiếm sản phẩm theo từ khóa và phân trang
	 * URL: /product/search-and-page
	 * Method: GET hoặc POST
	 * 
	 * @param model Model để truyền dữ liệu sang view
	 * @param keywords Từ khóa tìm kiếm (Optional)
	 * @param p Số trang hiện tại (Optional, mặc định là 0)
	 * @return Tên view để hiển thị
	 */
	@RequestMapping("/product/search-and-page")
	public String searchAndPage(Model model,
			@RequestParam("keywords") Optional<String> kw,
			@RequestParam("p") Optional<Integer> p) {
		
		// Lấy từ khóa từ request param hoặc từ session (nếu đã tìm kiếm trước đó)
		// Nếu không có từ request param, lấy từ session, nếu session cũng null thì dùng ""
		String kwords = kw.orElse(session.get("keywords") != null ? session.get("keywords") : "");
		
		// Lưu từ khóa vào session để duy trì khi chuyển trang
		session.set("keywords", kwords);
		
		// Lấy số trang, đảm bảo không bao giờ âm (tối thiểu là 0)
		int pageNumber = p.orElse(0);
		if (pageNumber < 0) {
			pageNumber = 0;
		}
		
		// Tạo đối tượng Pageable: trang số p (mặc định 0), mỗi trang 5 sản phẩm
		Pageable pageable = PageRequest.of(pageNumber, 5);
		
		// Gọi method findByKeywords() với từ khóa có % ở đầu và cuối (LIKE %keyword%)
		Page<Product> page = dao.findByKeywords("%" + kwords + "%", pageable);
		
		// Thêm đối tượng page vào model để hiển thị trên view
		model.addAttribute("page", page);
		// Thêm từ khóa vào model để hiển thị lại trong ô input
		model.addAttribute("keywords", kwords);
		
		return "product/search-and-page";
	}
}
