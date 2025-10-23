package poly.edu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.edu.entity.Account;

// DAO (Data Access Object) cho Account entity
// Kế thừa JpaRepository để có sẵn các phương thức CRUD
public interface AccountDAO extends JpaRepository<Account, String> {
    // JpaRepository cung cấp sẵn: findById, findAll, save, delete, etc.
}
