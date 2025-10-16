package poly.edu.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import poly.edu.entity.Order;

// Interface DAO để làm việc với bảng Orders
public interface OrderDAO extends JpaRepository<Order, Long> {
    // Các phương thức CRUD được kế thừa từ JpaRepository
}
