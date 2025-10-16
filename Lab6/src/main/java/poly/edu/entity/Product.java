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

// Entity đại diện cho bảng Products trong CSDL
@Entity
@Table(name = "Products")
public class Product implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tự động tăng ID
    private Integer id; // Mã sản phẩm
    
    private String name; // Tên sản phẩm
    
    private String image; // Đường dẫn hình ảnh
    
    private Double price; // Giá sản phẩm
    
    @Temporal(TemporalType.DATE) // Chỉ lưu ngày, không lưu giờ
    @Column(name = "Createdate")
    private Date createDate = new Date(); // Ngày tạo
    
    private Boolean available; // Trạng thái còn hàng
    
    // Quan hệ n-1 với Category (nhiều products thuộc 1 category)
    @ManyToOne
    @JoinColumn(name = "Categoryid")
    private Category category;
    
    // Quan hệ 1-n với OrderDetail (1 product có nhiều order details)
    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetails;

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
