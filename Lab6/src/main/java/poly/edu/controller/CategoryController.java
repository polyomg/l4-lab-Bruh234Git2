package poly.edu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.edu.dao.CategoryDAO;
import poly.edu.entity.Category;

// Controller xử lý các request liên quan đến Category
@Controller
public class CategoryController {
    
    @Autowired
    CategoryDAO dao; // Inject CategoryDAO để làm việc với CSDL
    
    // Hiển thị trang quản lý category
    @RequestMapping("/category/index")
    public String index(Model model) {
        Category item = new Category(); // Tạo đối tượng category mới
        model.addAttribute("item", item); // Đưa vào model để hiển thị form
        List<Category> items = dao.findAll(); // Lấy tất cả categories từ CSDL
        model.addAttribute("items", items); // Đưa vào model để hiển thị table
        return "category/index"; // Trả về view category/index.html
    }
    
    // Xử lý chức năng chỉnh sửa category
    @RequestMapping("/category/edit/{id}")
    public String edit(Model model, @PathVariable("id") String id) {
        Category item = dao.findById(id).get(); // Tìm category theo id
        model.addAttribute("item", item); // Đưa vào model để hiển thị form
        List<Category> items = dao.findAll(); // Lấy tất cả categories
        model.addAttribute("items", items); // Đưa vào model để hiển thị bảng
        return "category/index"; // Trả về view category/index.html
    }
    
    // Xử lý chức năng tạo mới category
    @RequestMapping("/category/create")
    public String create(Category item) {
        dao.save(item); // Lưu category vào CSDL
        return "redirect:/category/index"; // Chuyển hướng về trang index
    }
    
    // Xử lý chức năng cập nhật category
    @RequestMapping("/category/update")
    public String update(Category item) {
        dao.save(item); // Cập nhật category trong CSDL
        return "redirect:/category/edit/" + item.getId(); // Chuyển hướng về trang edit
    }
    
    // Xử lý chức năng xóa category
    @RequestMapping("/category/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        dao.deleteById(id); // Xóa category theo id
        return "redirect:/category/index"; // Chuyển hướng về trang index
    }
}
