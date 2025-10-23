package poly.edu.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class đại diện cho bảng Product trong database
 * Sử dụng JPA để map với bảng trong cơ sở dữ liệu
 */
@Data // Lombok tự động tạo getter, setter, toString, equals, hashCode
@NoArgsConstructor // Tạo constructor không tham số
@AllArgsConstructor // Tạo constructor với tất cả tham số
@Entity // Đánh dấu đây là một entity JPA
@Table(name = "Products") // Map với bảng Products trong database
public class Product implements Serializable {
	
	@Id // Đánh dấu đây là khóa chính
	@Column(name = "Id") // Map với cột Id trong database
	private Integer id;
	
	@Column(name = "Name") // Map với cột Name
	private String name;
	
	@Column(name = "Price") // Map với cột Price
	private Double price;
	
	@Temporal(TemporalType.DATE) // Chỉ lưu ngày, không lưu giờ
	@Column(name = "CreateDate") // Map với cột CreateDate
	private Date createDate;
	
	@Column(name = "Category") // Map với cột Category (loại hàng)
	private String category;
}
