package poly.edu.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import poly.edu.entity.Category;

// Interface DAO để làm việc với bảng Categories
// JpaRepository cung cấp sẵn các phương thức CRUD cơ bản
public interface CategoryDAO extends JpaRepository<Category, String> {
    // Các phương thức CRUD được kế thừa từ JpaRepository:
    // - findAll(): lấy tất cả categories
    // - findById(id): tìm category theo id
    // - save(entity): thêm/cập nhật category
    // - deleteById(id): xóa category theo id
}
