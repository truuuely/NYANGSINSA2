package com.wan.nss.biz.product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.wan.nss.biz.image.ImageVO;

@Repository("productDAO")
public class ProductDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final String SQL_INSERT = "INSERT INTO PRODUCT (P_NM, P_CATEGORY, PRICE, P_STOCK, P_DETAIL, DC_PERCENT) VALUES(?, ?, ?, ?, ?, ?)";
	private final String SQL_SELECTONE = "SELECT * FROM PRODUCT WHERE P_NO = ?"; 
	private final String SQL_SELECTALL = "SELECT * FROM PRODUCT ORDER BY P_NO ASC"; // 상품 전체 보기
	private final String SQL_SELECTALL_PNAME = "SELECT * FROM PRODUCT WHERE P_NM LIKE CONCAT('%',?,'%')"; // 상품 이름 검색
	private final String SQL_SELECTALL_CATEGORY = "SELECT * FROM PRODUCT WHERE CATEGORY= ? AND PRICE BETWEEN ? AND ? ORDER BY P_NO DESC"; // 상품카테고리별
	private final String SQL_SELECTALL_NEW = "SELECT * FROM PRODUCT WHERE PRICE BETWEEN ? AND ? ORDER BY P_NO DESC"; // 상품
	private final String SQL_SELECTALL_DC = "SELECT * FROM PRODUCT WHERE DC_PERCENT>0"; // 할인 상품 검색
	private final String SQL_SELECTALL_IMAGE = "SELECT p.P_NO, p.P_NM, p.P_CATEGORY, p.PRICE, p.P_STOCK, p.P_DETAIL, p.DC_PERCENT, i.I_NM"
												+ " FROM PRODUCT p " + "INNER JOIN IMAGE i " + "   ON p.P_NO = i.TARGET_NO AND i.TYPE_NO = ?;";

	private final String SQL_SELECTALL_INFO_IMAGE = "SELECT p.P_NO, p.P_NM, p.P_CATEGORY, p.PRICE, p.P_STOCK, p.P_DETAIL, p.DC_PERCENT, i.I_NM"
												+ " FROM PRODUCT p " + "INNER JOIN IMAGE i " + "   ON p.P_NO = i.TARGET_NO AND i.TYPE_NO = ?;";

	private final String SQL_UPDATE = "UPDATE PRODUCT SET P_NM=?, P_CATEGORY=?, PRICE=?, P_DETAIL=?, DC_PERCENT=? WHERE P_NO=?";
	private final String SQL_UPDATE_STOCK = "UPDATE PRODUCT SET P_SOTCK = P_STOCK - ? WHERE P_NO = ?"; // 상품 재고 수량
	private final String SQL_DELETE = "DELETE FROM PRODUCT WHERE P_NO=?";

	public boolean insert(ProductVO pvo) { // 회원가입
		jdbcTemplate.update(SQL_INSERT, pvo.getpName(), pvo.getpCategory(), pvo.getPrice(), pvo.getpStock(),
				pvo.getpDetail(), pvo.getpDcPercent());
		return true;
	}

	public boolean update(ProductVO pvo) {
		if (pvo.getpStock() >= 0) {
			jdbcTemplate.update(SQL_UPDATE_STOCK, pvo.getpStock(), pvo.getpNum());
			return true;
		} else {
			jdbcTemplate.update(SQL_UPDATE, pvo.getpName(), pvo.getpCategory(), pvo.getPrice(), pvo.getpStock(),
					pvo.getpDetail(), pvo.getpDcPercent(), pvo.getpNum());
			return true;
		}
	}

	public boolean delete(ProductVO pvo) {
		jdbcTemplate.update(SQL_DELETE, pvo.getpNum());
		return true;
	}

	public ArrayList<ProductVO> selectAll(ProductVO pvo) {
		ImageVO ivo = new ImageVO();
		// 상품 이름으로 검색
		if (pvo.getpName() != null) {
			Object[] args = { pvo.getpName() };
			return (ArrayList<ProductVO>) jdbcTemplate.query(SQL_SELECTALL_PNAME, args, new ProductRowMapper());
		} else if (pvo.getpCategory() != null) {
			Object[] args = { pvo.getpCategory() };
			return (ArrayList<ProductVO>) jdbcTemplate.query(SQL_SELECTALL_CATEGORY, args, new ProductRowMapper());

		} else if (pvo.getPrice() >= 0) {
			Object[] args = { pvo.getPrice() };
			return (ArrayList<ProductVO>) jdbcTemplate.query(SQL_SELECTALL_NEW, args, new ProductRowMapper());

		} else if (pvo.getpDcPercent() >= 0) {
			Object[] args = { pvo.getpDcPercent() };
			return (ArrayList<ProductVO>) jdbcTemplate.query(SQL_SELECTALL_DC, args, new ProductRowMapper());
		}
		// SQL문의 where 절에 Image 테이블 TYPE_NO 컬럼이 필요한데, vo에 I_NM만 멤버변수로 가지고 있으면 불러올 수가 없을
		// 것 같아서
		// ImageVO 제작해서 객체화하고 typeNum 불러와서 값 넣음.
		else if (ivo.getTypeNum() == 101) { // 대표 이미지
			Object[] args = { ivo.getTypeNum() };
			return (ArrayList<ProductVO>) jdbcTemplate.query(SQL_SELECTALL_IMAGE, args, new ProductRowMapper());
		} else if (ivo.getTypeNum() == 102) { // 상세 이미지
			Object[] args = { ivo.getTypeNum() };
			return (ArrayList<ProductVO>) jdbcTemplate.query(SQL_SELECTALL_INFO_IMAGE, args, new ProductRowMapper());
		} else {
			return (ArrayList<ProductVO>) jdbcTemplate.query(SQL_SELECTALL, new ProductRowMapper());
		}
	}

	public ProductVO selectOne(ProductVO pvo) {
		Object[] args = { pvo.getpNum() };
		return jdbcTemplate.queryForObject(SQL_SELECTONE, args, new ProductRowMapper());
	}

	class ProductRowMapper implements RowMapper<ProductVO> {

		@Override
		public ProductVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			ProductVO pvo = new ProductVO();
			pvo.setpNum(rs.getInt("P_NO"));
			pvo.setpName(rs.getString("P_NM"));
			pvo.setpCategory(rs.getString("P_CATEGORY"));
			pvo.setPrice(rs.getInt("PRICE"));
			pvo.setpStock(rs.getInt("P_STOCK"));
			pvo.setpDetail(rs.getString("P_DETAIL"));
			pvo.setpDcPercent(rs.getInt("DC_PERCENT"));

//         pvo.setSearchLowPrice(rs.getInt("LOWPRICE"));
//         pvo.setSearchHighPrice(rs.getInt("HIGHPRICE"));
//         pvo.setTotal(rs.getInt("TOTAL"));
//         pvo.setDc_price(rs.getInt("DC_PRICE"));
//         pvo.setpCnt(rs.getInt("PCNT"));
//         pvo.setpSearchContent(rs.getString("CONTENT"));
//         pvo.setSort(rs.getString("SORT"));
//         pvo.setImageName(rs.getString("IMAGENAME"));
			return pvo;
		}
	}
}