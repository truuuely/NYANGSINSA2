package com.wan.nss.biz.product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository("productDAO")
public class ProductDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String SQL_INSERT = "INSERT INTO PRODUCT (P_NM, P_CATEGORY, PRICE, P_STOCK, P_DETAIL, DC_PERCENT) VALUES(?, ?, ?, ?, ?, ?)";
	private final String SQL_SELECTONE = "SELECT * FROM PRODUCT WHERE P_NO = ?"; // 상품 상세 검색
	private final String SQL_SELECTALL = "SELECT * FROM PRODUCT ORDER BY P_NO ASC"; // 상품 전체 보기
	private final String SQL_SELECTALL_PNAME = "SELECT * FROM PRODUCT WHERE P_NM LIKE CONCAT('%',?,'%')"; // 상품 이름 검색
	private final String SQL_SELECTALL_CATEGORY = "SELECT * FROM PRODUCT WHERE CATEGORY= ? AND PRICE BETWEEN ? AND ? ORDER BY P_NO DESC"; // 상품카테고리별
	private final String SQL_SELECTALL_NEW = "SELECT * FROM PRODUCT WHERE PRICE BETWEEN ? AND ? ORDER BY P_NO DESC"; // 상품 신상품 검색
	private final String SQL_SELECTALL_DC = "SELECT * FROM PRODUCT WHERE DC_PERCENT>0"; // 할인 상품 검색
	private final String SQL_UPDATE = "UPDATE PRODUCT SET P_NM=?, P_CATEGORY=?, PRICE=?, P_STOCK=?, P_DETAIL=?, DC_PERCENT=? WHERE P_NO=?";
	private final String SQL_UPDATE_SOTCK = "UPDATE PRODUCT SET P_SOTCK = P_SOTCK - ? WHERE P_NO = ?"; // 상품 재고 수량
	private final String SQL_DELETE = "DELETE FROM PRODUCT WHERE P_NO=?";
	
	public boolean insert(ProductVO pvo) { // 회원가입
		jdbcTemplate.update(SQL_INSERT, pvo.getpName(), pvo.getpCategory(), pvo.getPrice(), pvo.getpStock(), pvo.getpDetail(), pvo.getpDcPercent());
	      return true;
	   }
	
	public boolean update(ProductVO pvo) {
		jdbcTemplate.update(SQL_UPDATE, pvo.getpName(), pvo.getpCategory(), pvo.getPrice(), pvo.getpStock(), pvo.getpDetail(), pvo.getpDcPercent(), pvo.getpNum());
		return true;
	}
	
	public boolean delete (ProductVO pvo) {
		jdbcTemplate.update(SQL_DELETE, pvo.getpNum());
		return true;
	}
	
	public ProductVO selectOne(ProductVO pvo) {
		try {
			
			Object[] args= { pvo.getpNum() };
			return jdbcTemplate.queryForObject(SQL_SELECTONE, args, new ProductRowMapper());
			
		} catch(Exception e) {
			return null;
		} 
		
	}
	
	public ArrayList<ProductVO> selectAll (ProductVO pvo){
		if () {
			
		}
	}
	public ArrayList<ProductVO> selectAll (ProductVO pvo) {
		Object[] args = { pvo.getpName()};
		return (ArrayList<ProductVO>) jdbcTemplate.query(SQL_SELECTALL_PNAME, args, new ProductRowMapper());	
	}
	
	
	
	class ProductRowMapper implements RowMapper<ProductVO> {

		@Override
		public ProductVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			ProductVO pvo = new ProductVO();
			pvo.setpNum(rs.getInt("PNUM")); 
			pvo.setpName(rs.getString("PNAME"));
			pvo.setpCategory(rs.getString("PCATEGORY"));
			pvo.setPrice(rs.getInt("PRICE"));
			pvo.setpStock(rs.getInt("STOCK"));
			pvo.setpDetail(rs.getString("DETAIL"));
			pvo.setpDcPercent(rs.getInt("DCPERCENT"));
			pvo.setSearchLowPrice(rs.getInt("LOWPRICE"));
			pvo.setSearchHighPrice(rs.getInt("HIGHPRICE"));
			pvo.setTotal(rs.getInt("TOTAL"));
			pvo.setDc_price(rs.getInt("DC_PRICE"));
			pvo.setpCnt(rs.getInt("PCNT"));
			pvo.setpSearchContent(rs.getString("CONTENT"));
			pvo.setSort(rs.getString("SORT"));
			pvo.setImageName(rs.getString("IMAGENAME"));
			return pvo;
		}
	}
}
