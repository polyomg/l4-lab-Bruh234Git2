package poly.edu.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

// Entity đại diện cho bảng Orders trong CSDL
@Entity
@Table(name = "Orders")
public class Order implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tự động tăng ID
    private Long id; // Mã đơn hàng
    
    private String address; // Địa chỉ giao hàng
    
    @Temporal(TemporalType.DATE) // Chỉ lưu ngày
    @Column(name = "Createdate")
    private Date createDate = new Date(); // Ngày tạo đơn
    
    // Quan hệ n-1 với Account (nhiều orders thuộc 1 account)
    @ManyToOne
    @JoinColumn(name = "Username")
    private Account account;
    
    // Quan hệ 1-n với OrderDetail (1 order có nhiều order details)
    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
