package poly.dao;

import java.util.List;

import poly.entity.NEWS;


public interface NEWSDAO {
	/** Truy vấn tất cả các phòng ban */
    List<NEWS> findAll();
    
    /** Truy vấn phòng ban theo mã */
    NEWS findById(String id);
    
    /** Thêm mới một phòng ban */
    void create(NEWS item);
    
    /** Cập nhật thông tin phòng ban */
    void update(NEWS item);
    
    /** Xóa phòng ban theo mã */
    void deleteById(String id);
}
