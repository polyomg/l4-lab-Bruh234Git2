package poly.edu.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

// Entity đại diện cho bảng Accounts trong database
@Data // Lombok tự động tạo getter/setter, toString, equals, hashCode
@Entity // Đánh dấu đây là JPA entity
@Table(name = "Accounts") // Ánh xạ với bảng Accounts
public class Account implements Serializable {

    // Khóa chính
    @Id
    private String username; // Tên đăng nhập

    private String password;  // Mật khẩu
    private String fullname;  // Họ và tên
    private String email;     // Email
    private String photo;     // Ảnh đại diện
    private boolean activated; // Trạng thái kích hoạt
    private boolean admin;    // Quyền admin

    // Mối quan hệ 1-n với Order
    @OneToMany(mappedBy = "account")
    private List<Order> orders; // Danh sách đơn hàng của tài khoản
}
