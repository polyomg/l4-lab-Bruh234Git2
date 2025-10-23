# LAB7: JPAREPOSITORY 2

## 📋 Mục tiêu
Bài thực hành này giúp bạn có khả năng cũng có lại kiến thức:
- ✅ Sử dụng @Query để thực hiện truy vấn tùy biến theo JPQL
- ✅ Truy vấn tổng hợp dữ liệu
- ✅ Kết hợp JPQL với sắp xếp và phân trang
- ✅ Sử dụng DSL để truy vấn dữ liệu, kết hợp với sắp xếp và phân trang

## 🏗️ Cấu trúc Project

```
Lab7/
├── src/main/java/poly/edu/
│   ├── controller/
│   │   ├── HomeController.java          # Controller trang chủ
│   │   ├── ProductController.java       # Controller xử lý tìm kiếm sản phẩm
│   │   └── ReportController.java        # Controller xử lý báo cáo
│   ├── dao/
│   │   └── ProductDAO.java              # Interface JPA Repository với @Query và DSL
│   ├── entity/
│   │   ├── Product.java                 # Entity Product (sản phẩm)
│   │   └── Report.java                  # Interface Report (báo cáo tổng hợp)
│   ├── service/
│   │   └── SessionService.java          # Service quản lý session
│   └── Java5Lab7Application.java        # Main application class
├── src/main/resources/
│   ├── templates/
│   │   ├── product/
│   │   │   ├── search.html              # View tìm kiếm theo giá
│   │   │   └── search-and-page.html     # View tìm kiếm và phân trang
│   │   ├── report/
│   │   │   └── inventory-by-category.html # View báo cáo tồn kho
│   │   └── index.html                   # Trang chủ
│   ├── application.properties           # Cấu hình ứng dụng
│   └── import.sql                       # Dữ liệu mẫu tự động import
└── pom.xml                              # Maven dependencies
```

## 🎯 Các bài tập đã hoàn thành

### Bài 1 (2 điểm): Tìm kiếm sản phẩm theo khoảng giá
- **URL**: http://localhost:8080/product/search
- **Mô tả**: Tìm kiếm sản phẩm có giá trong khoảng min-max
- **Công nghệ**: Sử dụng @Query với JPQL
- **File liên quan**:
  - Controller: `ProductController.search()`
  - DAO: `ProductDAO.findByPrice()`
  - View: `product/search.html`

### Bài 2 (2 điểm): Tìm kiếm và phân trang
- **URL**: http://localhost:8080/product/search-and-page
- **Mô tả**: Tìm kiếm sản phẩm theo từ khóa với phân trang
- **Công nghệ**: Sử dụng @Query với Pageable
- **File liên quan**:
  - Controller: `ProductController.searchAndPage()`
  - DAO: `ProductDAO.findByKeywords()`
  - View: `product/search-and-page.html`

### Bài 3 (1 điểm): Báo cáo tồn kho theo loại
- **URL**: http://localhost:8080/report/inventory-by-category
- **Mô tả**: Hiển thị báo cáo tổng hợp theo category
- **Công nghệ**: Sử dụng @Query với GROUP BY
- **File liên quan**:
  - Controller: `ReportController.inventory()`
  - DAO: `ProductDAO.getInventoryByCategory()`
  - Entity: `Report.java` (interface projection)
  - View: `report/inventory-by-category.html`

### Bài 4 & 5: Sử dụng DSL (Derived Query Methods)
- **Mô tả**: Thay thế @Query bằng DSL methods
- **File liên quan**:
  - DAO: `ProductDAO.findByPriceBetween()`, `ProductDAO.findByNameLike()`
- **Cách hoạt động**: Spring Data JPA tự động tạo query từ tên method

## 🚀 Cách chạy ứng dụng

### Yêu cầu
- Java 17 trở lên
- Maven 3.6+
- IDE (Eclipse, IntelliJ IDEA, hoặc VS Code)

### Các bước thực hiện

1. **Clone hoặc mở project trong IDE**

2. **Build project với Maven**
   ```bash
   mvn clean install
   ```

3. **Chạy ứng dụng**
   ```bash
   mvn spring-boot:run
   ```
   Hoặc chạy file `Java5Lab7Application.java` trong IDE

4. **Truy cập ứng dụng**
   - Trang chủ: http://localhost:8080
   - H2 Console: http://localhost:8080/h2-console
     - JDBC URL: `jdbc:h2:mem:testdb`
     - Username: `sa`
     - Password: (để trống)

## 💡 Giải thích cách hoạt động

### 1. Entity và Database

**Product.java**
```java
@Entity
@Table(name = "Products")
public class Product {
    @Id
    private Integer id;
    private String name;
    private Double price;
    @Temporal(TemporalType.DATE)
    private Date createDate;
    private String category;
}
```
- `@Entity`: Đánh dấu class là một entity JPA
- `@Table`: Map với bảng Products trong database
- `@Id`: Đánh dấu khóa chính
- `@Temporal`: Chỉ định kiểu dữ liệu ngày tháng

**Report.java** (Interface Projection)
```java
public interface Report {
    Serializable getGroup();  // Nhóm (category)
    Double getSum();          // Tổng giá
    Long getCount();          // Số lượng
}
```
- Interface này không map với bảng
- JPA tự động implement các getter
- Dùng để nhận kết quả từ query tổng hợp

### 2. Repository với @Query và DSL

**@Query với JPQL**
```java
@Query("FROM Product o WHERE o.price BETWEEN ?1 AND ?2")
List<Product> findByPrice(double minPrice, double maxPrice);
```
- JPQL: Java Persistence Query Language (tương tự SQL)
- `?1`, `?2`: Tham số thứ 1, thứ 2
- `FROM Product`: Query trên entity, không phải table

**@Query với GROUP BY**
```java
@Query("SELECT o.category AS group, sum(o.price) AS sum, count(o) AS count "
    + "FROM Product o GROUP BY o.category ORDER BY sum(o.price) DESC")
List<Report> getInventoryByCategory();
```
- `AS group`, `AS sum`, `AS count`: Alias map với Report interface
- `GROUP BY`: Nhóm theo category
- `ORDER BY ... DESC`: Sắp xếp giảm dần

**DSL (Derived Query Methods)**
```java
List<Product> findByPriceBetween(double minPrice, double maxPrice);
Page<Product> findByNameLike(String keywords, Pageable pageable);
```
- Spring tự động tạo query từ tên method
- `findBy`: Tìm kiếm
- `PriceBetween`: WHERE price BETWEEN ? AND ?
- `NameLike`: WHERE name LIKE ?

### 3. Controller

**ProductController**
- `@Autowired ProductDAO dao`: Inject DAO để thao tác database
- `@RequestParam Optional<Double> min`: Nhận tham số từ request (có thể null)
- `model.addAttribute("items", items)`: Truyền dữ liệu sang view
- `PageRequest.of(p, 5)`: Tạo Pageable với trang p, mỗi trang 5 items

### 4. View (Thymeleaf)

**Hiển thị danh sách**
```html
<th:block th:each="item: ${items}">
    <tr>
        <td th:text="${item.id}"></td>
        <td th:text="${item.name}"></td>
    </tr>
</th:block>
```
- `th:each`: Vòng lặp
- `th:text`: Hiển thị giá trị
- `${items}`: Lấy dữ liệu từ model

**Phân trang**
```html
<a th:href="@{/product/search-and-page(p=${page.number + 1})}">Next</a>
```
- `@{...}`: Thymeleaf URL expression
- `(p=...)`: Thêm tham số vào URL

**Thông tin Page**
- `${page.content}`: Danh sách items trong trang hiện tại
- `${page.number}`: Số trang hiện tại (bắt đầu từ 0)
- `${page.totalPages}`: Tổng số trang
- `${page.totalElements}`: Tổng số items

### 5. Configuration

**application.properties**
```properties
# H2 Database (in-memory)
spring.datasource.url=jdbc:h2:mem:testdb
spring.jpa.hibernate.ddl-auto=create-drop
spring.h2.console.enabled=true
```
- H2: Database nhẹ, chạy trong RAM
- `create-drop`: Tạo lại schema mỗi lần chạy
- `h2.console.enabled`: Bật web console để xem database

**import.sql**
- File này được Hibernate tự động thực thi khi khởi động
- Dùng để insert dữ liệu mẫu vào database

## 📊 Database Schema

```sql
CREATE TABLE Products (
    Id INT PRIMARY KEY,
    Name VARCHAR(255),
    Price DOUBLE,
    Create_Date DATE,
    Category VARCHAR(255)
);
```

## 🔍 Testing

### Test Bài 1: Search by Price
1. Truy cập: http://localhost:8080/product/search
2. Nhập Min Price: 5, Max Price: 8
3. Click Search
4. Kết quả: Hiển thị các sản phẩm có giá từ 5-8

### Test Bài 2: Search and Page
1. Truy cập: http://localhost:8080/product/search-and-page
2. Nhập từ khóa: "an"
3. Click Search
4. Kết quả: Hiển thị các sản phẩm có tên chứa "an", phân trang 5 items/trang
5. Click Next/Previous để chuyển trang

### Test Bài 3: Inventory Report
1. Truy cập: http://localhost:8080/report/inventory-by-category
2. Kết quả: Hiển thị báo cáo tổng hợp theo loại hàng
   - Loại hàng (category)
   - Tổng giá (sum)
   - Số sản phẩm (count)

## 📚 Kiến thức đã áp dụng

### JPA & Hibernate
- Entity mapping với @Entity, @Table, @Id
- Temporal types với @Temporal
- JpaRepository interface
- JPQL (Java Persistence Query Language)
- Projection với interface

### Spring Data JPA
- @Query annotation
- Pageable và Page
- DSL (Derived Query Methods)
- Repository pattern

### Spring MVC
- @Controller, @RequestMapping
- @RequestParam với Optional
- Model để truyền dữ liệu
- @Autowired dependency injection

### Thymeleaf
- Template engine
- th:each, th:text, th:href
- URL expressions @{...}
- Variable expressions ${...}

## 🎓 Tác giả
- **Lab**: Lab7 - JpaRepository 2
- **Môn học**: Lập trình Java 5
- **Trường**: FPT Polytechnic

## 📝 Ghi chú
- Project sử dụng H2 in-memory database nên dữ liệu sẽ mất khi tắt ứng dụng
- Mỗi lần chạy lại, dữ liệu sẽ được tạo lại từ file import.sql
- Có thể xem database qua H2 Console tại http://localhost:8080/h2-console
