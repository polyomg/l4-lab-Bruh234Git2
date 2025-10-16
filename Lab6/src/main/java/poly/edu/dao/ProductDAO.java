package poly.edu.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import poly.edu.entity.Product;

// Interface DAO để làm việc với bảng Products
// JpaRepository cung cấp sẵn các phương thức CRUD và phân trang/sắp xếp
public interface ProductDAO extends JpaRepository<Product, Integer> {
    // Các phương thức được kế thừa từ JpaRepository:
    // - findAll(): lấy tất cả products
    // - findAll(Sort): lấy tất cả products và sắp xếp
    // - findAll(Pageable): lấy products theo trang
    // - findById(id): tìm product theo id
    // - save(entity): thêm/cập nhật product
    // - deleteById(id): xóa product theo id
}
