package poly.edu.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

// Entity đại diện cho bảng Orderdetails trong CSDL
@Entity
@Table(name = "Orderdetails")
public class OrderDetail implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tự động tăng ID
    private Long id; // Mã chi tiết đơn hàng
    
    private Double price; // Giá sản phẩm tại thời điểm đặt
    
    private Integer quantity; // Số lượng
    
    // Quan hệ n-1 với Product (nhiều order details thuộc 1 product)
    @ManyToOne
    @JoinColumn(name = "Productid")
    private Product product;
    
    // Quan hệ n-1 với Order (nhiều order details thuộc 1 order)
    @ManyToOne
    @JoinColumn(name = "Orderid")
    private Order order;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
