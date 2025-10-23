package poly.edu.entity;

import java.io.Serializable;

/**
 * Interface Report dùng để chứa kết quả truy vấn tổng hợp từ database
 * Không map trực tiếp với bảng mà dùng để nhận kết quả từ các query phức tạp
 * Đây là projection interface - JPA sẽ tự động implement các getter
 */
public interface Report extends Serializable {
	
	/**
	 * Lấy nhóm/loại (ví dụ: category của sản phẩm)
	 * JPA sẽ tự động map kết quả từ query vào method này
	 */
	Serializable getGroup();
	
	/**
	 * Lấy tổng giá trị (sum) của nhóm
	 */
	Double getSum();
	
	/**
	 * Lấy số lượng (count) sản phẩm trong nhóm
	 */
	Long getCount();
}
