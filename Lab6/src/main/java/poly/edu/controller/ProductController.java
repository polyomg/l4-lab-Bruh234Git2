package poly.edu.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poly.edu.dao.ProductDAO;
import poly.edu.entity.Product;

// Controller xử lý các request liên quan đến Product
@Controller
public class ProductController {
    
    @Autowired
    ProductDAO dao; // Inject ProductDAO để làm việc với CSDL
    
    // Hiển thị trang sắp xếp sản phẩm
    @RequestMapping("/product/sort")
    public String sort(Model model, @RequestParam("field") Optional<String> field) {
        // Tạo đối tượng Sort để sắp xếp
        // Nếu field không được chọn, mặc định sắp xếp theo price
        Sort sort = Sort.by(Direction.DESC, field.orElse("price"));
        
        // Lấy tất cả products và sắp xếp
        List<Product> items = dao.findAll(sort);
        
        // Đưa field và items vào model
        model.addAttribute("field", field.orElse("price").toUpperCase());
        model.addAttribute("items", items);
        
        return "product/sort"; // Trả về view product/sort.html
    }
    
    // Hiển thị trang phân trang sản phẩm
    @RequestMapping("/product/page")
    public String paginate(Model model, @RequestParam("p") Optional<Integer> p) {
        // Tạo đối tượng Pageable để phân trang
        // Trang mặc định là 0, mỗi trang 5 sản phẩm
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        
        // Lấy products theo trang
        Page<Product> page = dao.findAll(pageable);
        
        // Đưa page và page vào model
        model.addAttribute("page", page);
        
        return "product/page"; // Trả về view product/page.html
    }
}
