package poly.edu.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import poly.edu.entity.OrderDetail;

// Interface DAO để làm việc với bảng Orderdetails
public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long> {
    // Các phương thức CRUD được kế thừa từ JpaRepository
}
