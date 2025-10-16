package poly.edu.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import poly.edu.entity.Account;

// Interface DAO để làm việc với bảng Accounts
public interface AccountDAO extends JpaRepository<Account, String> {
    // Các phương thức CRUD được kế thừa từ JpaRepository
}
