package poly.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.edu.dao.AccountDAO;
import poly.edu.entity.Account;
import poly.edu.service.AccountService;

// Service implementation cho AccountService
@Service
public class AccountServiceImpl implements AccountService {

    // Inject AccountDAO để truy xuất dữ liệu
    @Autowired
    AccountDAO dao;

    // Tìm tài khoản theo username
    @Override
    public Account findById(String username) {
        // Gọi DAO để tìm, trả về null nếu không tìm thấy
        return dao.findById(username).orElse(null);
    }

	// Lấy danh sách tất cả tài khoản
	@Override
	public List<Account> findAll() {
		// Gọi DAO để lấy danh sách
		return dao.findAll();
	}
}
