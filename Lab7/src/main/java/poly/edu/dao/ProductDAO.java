package poly.edu.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import poly.edu.entity.Product;
import poly.edu.entity.Report;

/**
 * ProductDAO - Interface kế thừa JpaRepository để thao tác với entity Product
 * JpaRepository cung cấp sẵn các method CRUD cơ bản (save, findById, findAll, delete...)
 * Chúng ta thêm các method tùy chỉnh sử dụng @Query và DSL (Derived Query Methods)
 */
public interface ProductDAO extends JpaRepository<Product, Integer> {
	
	/**
	 * BÀI 1: Tìm sản phẩm theo khoảng giá sử dụng @Query với JPQL
	 * @Query cho phép viết câu truy vấn JPQL tùy chỉnh
	 * JPQL: Java Persistence Query Language - tương tự SQL nhưng làm việc với entity
	 * 
	 * @param minPrice Giá tối thiểu
	 * @param maxPrice Giá tối đa
	 * @return Danh sách sản phẩm có giá trong khoảng [minPrice, maxPrice]
	 */
	@Query("FROM Product o WHERE o.price BETWEEN ?1 AND ?2")
	List<Product> findByPrice(double minPrice, double maxPrice);
	
	/**
	 * BÀI 2: Tìm sản phẩm theo từ khóa trong tên và phân trang
	 * Sử dụng @Query với JPQL và Pageable để hỗ trợ phân trang
	 * LIKE %?%: Tìm kiếm gần đúng (contains)
	 * 
	 * @param keywords Từ khóa tìm kiếm
	 * @param pageable Đối tượng chứa thông tin phân trang (page number, size, sort)
	 * @return Page<Product> chứa danh sách sản phẩm và thông tin phân trang
	 */
	@Query("FROM Product o WHERE o.name LIKE ?1")
	Page<Product> findByKeywords(String keywords, Pageable pageable);
	
	/**
	 * BÀI 3: Lấy báo cáo tổng hợp theo loại sản phẩm (category)
	 * Sử dụng @Query với GROUP BY để tổng hợp dữ liệu
	 * AS group, AS sum, AS count: Đặt alias cho các cột kết quả
	 * Kết quả sẽ được map vào interface Report
	 * 
	 * @return Danh sách Report chứa thông tin tổng hợp theo category
	 */
	@Query("SELECT o.category AS group, sum(o.price) AS sum, count(o) AS count "
			+ "FROM Product o "
			+ "GROUP BY o.category "
			+ "ORDER BY sum(o.price) DESC")
	List<Report> getInventoryByCategory();
	
	/**
	 * BÀI 4: Tìm sản phẩm theo khoảng giá sử dụng DSL (Derived Query Method)
	 * DSL: Domain Specific Language - Spring Data tự động tạo query từ tên method
	 * findByPriceBetween: Spring hiểu là "SELECT * FROM Product WHERE price BETWEEN ? AND ?"
	 * Không cần viết @Query, Spring tự động generate SQL
	 * 
	 * @param minPrice Giá tối thiểu
	 * @param maxPrice Giá tối đa
	 * @return Danh sách sản phẩm có giá trong khoảng
	 */
	List<Product> findByPriceBetween(double minPrice, double maxPrice);
	
	/**
	 * BÀI 5: Tìm sản phẩm theo từ khóa trong tên và phân trang sử dụng DSL
	 * findByNameLike: Spring hiểu là "SELECT * FROM Product WHERE name LIKE ?"
	 * Kết hợp với Pageable để hỗ trợ phân trang
	 * 
	 * @param keywords Từ khóa tìm kiếm (cần thêm % ở đầu/cuối khi gọi)
	 * @param pageable Thông tin phân trang
	 * @return Page<Product> với kết quả phân trang
	 */
	Page<Product> findByNameLike(String keywords, Pageable pageable);
	
	/**
	 * BÀI 5 (phương án 2): Tìm tất cả sản phẩm có tên chứa từ khóa và phân trang
	 * findAllByNameLike: Tương tự findByNameLike nhưng rõ ràng hơn là lấy tất cả
	 * 
	 * @param keywords Từ khóa tìm kiếm
	 * @param pageable Thông tin phân trang
	 * @return Page<Product> với kết quả phân trang
	 */
	Page<Product> findAllByNameLike(String keywords, Pageable pageable);
}
