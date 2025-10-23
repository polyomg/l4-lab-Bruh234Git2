package poly.edu.service;

import java.util.List;

import poly.edu.entity.Account;

// Interface AccountService định nghĩa các phương thức xử lý logic liên quan đến Account
public interface AccountService {
    // Tìm tài khoản theo username
    Account findById(String username);

    // Lấy danh sách tất cả tài khoản
	List<Account> findAll();
}
