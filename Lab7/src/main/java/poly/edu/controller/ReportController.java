package poly.edu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.edu.dao.ProductDAO;
import poly.edu.entity.Report;

/**
 * ReportController - Controller xử lý các request liên quan đến báo cáo (Report)
 * Hiển thị các thống kê, tổng hợp dữ liệu từ database
 */
@Controller
public class ReportController {
	
	@Autowired
	ProductDAO dao; // Inject ProductDAO để lấy dữ liệu báo cáo
	
	/**
	 * BÀI 3: Hiển thị báo cáo tồn kho theo loại sản phẩm
	 * URL: /report/inventory-by-category
	 * Method: GET
	 * 
	 * Báo cáo này sẽ hiển thị:
	 * - Loại hàng (category)
	 * - Tổng giá trị (sum of price)
	 * - Số lượng sản phẩm (count)
	 * 
	 * @param model Model để truyền dữ liệu sang view
	 * @return Tên view để hiển thị báo cáo
	 */
	@RequestMapping("/report/inventory-by-category")
	public String inventory(Model model) {
		
		// Gọi method getInventoryByCategory() từ ProductDAO
		// Method này sử dụng @Query với GROUP BY để tổng hợp dữ liệu
		List<Report> items = dao.getInventoryByCategory();
		
		// Thêm danh sách Report vào model để hiển thị trên view
		model.addAttribute("items", items);
		
		// Trả về tên view (file HTML trong thư mục templates/report)
		return "report/inventory-by-category";
	}
}
