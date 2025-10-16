package poly.edu.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

// Entity đại diện cho bảng Categories trong CSDL
@Entity
@Table(name = "Categories")
public class Category implements Serializable {
    
    @Id
    private String id; // Mã danh mục
    
    private String name; // Tên danh mục
    
    // Quan hệ 1-n với Product (1 category có nhiều products)
    @OneToMany(mappedBy = "category")
    private List<Product> products;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
