package poly.edu.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

// Entity đại diện cho bảng Accounts trong CSDL
@Entity
@Table(name = "Accounts")
public class Account implements Serializable {
    
    @Id
    private String username; // Tên đăng nhập
    
    private String password; // Mật khẩu
    
    private String fullname; // Họ tên
    
    private String email; // Email
    
    private String photo; // Ảnh đại diện
    
    private boolean activated; // Trạng thái kích hoạt
    
    private boolean admin; // Quyền admin
    
    // Quan hệ 1-n với Order (1 account có nhiều orders)
    @OneToMany(mappedBy = "account")
    private List<Order> orders;

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
