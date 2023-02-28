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

	// 상품 추가
	private final String SQL_INSERT = "INSERT INTO PRODUCT (P_NM, P_CATEGORY, P_PRICE, P_STOCK, P_DETAIL, DC_PERCENT) VALUES(?, ?, ?, ?, ?, ?)";
	// 상품 상세 검색
	private final String SQL_SELECTONE = "SELECT * FROM PRODUCT WHERE P_NO = ?";
	// 상품 전체 보기
	private final String SQL_SELECTALL = "SELECT * FROM PRODUCT ORDER BY P_NO ASC";
	
	// 전체 상품 낮은 가격순
	private final String SQL_SELECTALL_PRODUCT_ASC = "SELECT P_NO, P_NM, P_CATEGORY, P_DETAIL, P_AMT, DC_PERCENT, "
			+ "P_PRICE*((100-PRODUCT.DC_PERCENT)/100) "
			+ "AS P_PRICE FROM PRODUCT WHERE P_PRICE BETWEEN ? AND ? ORDER BY P_PRICE ASC";
	
	// 전체 상품 높은 가격순
	private final String SQL_SELECTALL_PRODUCT_DESC = "SELECT P_NO, P_NM, P_CATEGORY, P_DETAIL, P_AMT, DC_PERCENT, "
			+ "P_PRICE*((100-PRODUCT.DC_PERCENT)/100) AS P_PRICE FROM PRODUCT WHERE P_PRICE BETWEEN ? AND ? ORDER BY P_PRICE DESC";

	// 상품 이름 검색
	private final String SQL_SELECTALL_PNAME = "SELECT * FROM PRODUCT WHERE P_NM LIKE CONCAT('%',?,'%')";
	// 상품 카테고리별
	private final String SQL_SELECTALL_CATEGORY = "SELECT * FROM PRODUCT WHERE P_CATEGORY= ? AND P_PRICE BETWEEN ? AND ? ORDER BY P_NO DESC";
	// 새상품
	private final String SQL_SELECTALL_NEW = "SELECT * FROM PRODUCT WHERE P_PRICE BETWEEN ? AND ? ORDER BY P_NO DESC";
	// 할인 상품 검색
	private final String SQL_SELECTALL_DC = "SELECT * FROM PRODUCT WHERE DC_PERCENT>0";
	// 상품 대표 이미지
	private final String SQL_SELECTALL_IMAGE = "SELECT p.P_NO, p.P_NM, p.P_CATEGORY, p.P_PRICE, p.P_AMT, p.P_DETAIL, p.DC_PERCENT, i.I_NM"
			+ " FROM PRODUCT p INNER JOIN IMAGE i ON p.P_NO = i.TARGET_NO AND i.TYPE_NO = ?;";
	
	// 인기 상품 검색
	private final String SQL_SELECTALL_POPULAR = "SELECT PRODUCT.P_NO, P_CATEGORY, P_NM, P_PRICE, DC_PERCENT, P_AMT, "
			+ " P_DETAIL, SUM(OD_CNT) FROM ORDER_DETAIL INNER JOIN PRODUCT ON ORDER_DETAIL.P_NO = PRODUCT.P_NO "
			+ " WHERE PRODUCT.P_PRICE BETWEEN ? AND ? GROUP BY PRODUCT.P_NO, P_CATEGORY, P_NM, P_PRICE, "
			+ " DC_PERCENT, P_AMT, P_DETAIL ORDER BY SUM(OD_CNT) DESC";
	
	// 상품 카테고리별 인기순
	final String SQL_SELECTALL_CATEGORY_SELLDESC = "SELECT PRODUCT.P_NO, P_CATEGORY, P_NM, P_PRICE, DC_PERCENT, P_AMT, "
			+ " P_DETAIL, SUM(OD_CNT) FROM ORDER_DETAIL INNER JOIN PRODUCT ON ORDER_DETAIL.P_NO = PRODUCT.P_NO AND P_CATEGORY= ?"
			+ " WHERE PRODUCT.P_PRICE  BETWEEN ? AND ? GROUP BY PRODUCT.P_NO, P_CATEGORY, P_NM, P_PRICE, "
			+ " DC_PERCENT, P_AMT, P_DETAIL ORDER BY SUM(OD_CNT) DESC";
	
	// 상품 카테고리별 낮은 가격순
	final String SQL_SELECTALL_CATEGORY_PRICEASC = "SELECT P_NO ,P_NM, P_CATEGORY, P_DETAIL, "
			+ " P_AMT,DC_PERCENT, P_PRICE*((100-PRODUCT.DC_PERCENT)/100) AS PRICE FROM PRODUCT WHERE P_CATEGORY= ? AND P_PRICE BETWEEN ? AND ? ORDER BY P_PRICE ASC"; // 상품
	
	// 상품 카테고리별 높은 가격순
	final String SQL_SELECTALL_CATEGORY_PRICEDESC = "SELECT P_NO, P_NM, P_CATEGORY, P_DETAIL, P_AMT, DC_PERCENT,"
			+ " P_PRICE*((100-PRODUCT.DC_PERCENT)/100) AS P_PRICE FROM PRODUCT "
			+ " WHERE P_CATEGORY= ? AND P_PRICE BETWEEN ? AND ? ORDER BY P_PRICE DESC";

	// 상품 카테고리별 최신순
	final String SQL_SELECTALL_CATEGORY_REGIDESC = "SELECT * FROM PRODUCT WHERE P_CATEGORY= ? AND P_PRICE BETWEEN ? AND ? ORDER BY P_NO DESC";
	
	// 상품 업데이트
	private final String SQL_UPDATE = "UPDATE PRODUCT SET P_NM=?, P_CATEGORY=?, P_PRICE=?, P_DETAIL=?, DC_PERCENT=? WHERE P_NO=?";
	
	// 상품 재고 수량 업데이트
	private final String SQL_UPDATE_AMOUNT = "UPDATE PRODUCT SET P_AMT = P_AMT - ? WHERE P_NO = ?";
	
	// 상품 삭제
	private final String SQL_DELETE = "DELETE FROM PRODUCT WHERE P_NO=?";
	

	// 상품 추가
	public boolean insert(ProductVO pvo) {
		jdbcTemplate.update(SQL_INSERT, pvo.getpName(), pvo.getCategory(), pvo.getPrice(), pvo.getpAmt(),
				pvo.getpDetail(), pvo.getpDcPercent());
		return true;
	}

	// 상품 & 재고 업데이트
	public boolean update(ProductVO pvo) {
		if (pvo.getpAmt() >= 0) {
			jdbcTemplate.update(SQL_UPDATE_AMOUNT, pvo.getpAmt(), pvo.getpNum());
			return true;
			
		} else {
			jdbcTemplate.update(SQL_UPDATE, pvo.getpName(), pvo.getCategory(), pvo.getPrice(), pvo.getpAmt(),
					pvo.getpDetail(), pvo.getpDcPercent(), pvo.getpNum());
			return true;
		}
	}

	// 상품 삭제
	public boolean delete(ProductVO pvo) {
		jdbcTemplate.update(SQL_DELETE, pvo.getpNum());
		return true;
	}

	public ArrayList<ProductVO> selectAll(ProductVO pvo) {
//		ImageVO ivo = new ImageVO();
		
		// pSearchCondition에 값이 있는 경우
		if(pvo.getpSearchCondition() != null) {
			if(pvo.getpSearchCondition().equals("all")) { 
				// 상품 전체 조회 
				System.out.println("	로그 jdbcTemplate: " + jdbcTemplate);
				return (ArrayList<ProductVO>) jdbcTemplate.query(SQL_SELECTALL, new ProductRowMapper());
			}
			else if (pvo.getpSearchCondition().equals("pName")) {
				// 상품 이름 검색
				Object[] args = { pvo.getpName() };
				return (ArrayList<ProductVO>) jdbcTemplate.query(SQL_SELECTALL_PNAME, args, new ProductRowMapper());
			
			}  else if (pvo.getpSearchCondition().equals("new")) { 
				// 새상품 검색
				Object[] args = { pvo.getSearchLowPrice(), pvo.getSearchHighPrice() };
				return (ArrayList<ProductVO>) jdbcTemplate.query(SQL_SELECTALL_NEW, args, new ProductRowMapper());

			} else if (pvo.getpSearchCondition().equals("dc")) { 
				//할인상품 
				Object[] args = { pvo.getpDcPercent() };
				return (ArrayList<ProductVO>) jdbcTemplate.query(SQL_SELECTALL_DC, args, new ProductRowMapper());
				
			} else if (pvo.getpSearchCondition().equals("popular")) { 
				// 인기 상품 검색
				Object[] args = { pvo.getSearchLowPrice(), pvo.getSearchHighPrice() };
				return (ArrayList<ProductVO>) jdbcTemplate.query(SQL_SELECTALL_POPULAR, args, new ProductRowMapper());
				
			} else if (pvo.getpSearchCondition().equals("related")) { 
				// 관련상품
				Object[] args = { pvo.getCategory(), pvo.getSearchLowPrice(), pvo.getSearchHighPrice() };
				return (ArrayList<ProductVO>) jdbcTemplate.query(SQL_SELECTALL_CATEGORY_SELLDESC, args, new ProductRowMapper());
				
			} else {
				// 모두 해당 안되면
				return null;
			}
		}
		else {
			if(!pvo.getCategory().equals("all") && pvo.getSort().equals("sellDesc")) {
				// 카테고리가 있고  sort가 sellDesc 카테고리별 인기순
				Object[] args = { pvo.getCategory(), pvo.getSearchLowPrice(), pvo.getSearchHighPrice() };
				return (ArrayList<ProductVO>) jdbcTemplate.query(SQL_SELECTALL_CATEGORY_SELLDESC, args, new ProductRowMapper());
		
			}  else if(!pvo.getCategory().equals("all") && pvo.getSort().equals("priceAsc")) {
				// 카테고리가 있고 sort가 PRICEASC 카테고리별 낮은 가격순
				Object[] args = { pvo.getCategory(), pvo.getSearchLowPrice(), pvo.getSearchHighPrice() };
				return (ArrayList<ProductVO>) jdbcTemplate.query(SQL_SELECTALL_CATEGORY_PRICEASC, args, new ProductRowMapper());
				
			}  else if(!pvo.getCategory().equals("all") && pvo.getSort().equals("priceDesc")) {
				// 카테고리가 있고 sort가 CATEGORY_PRICEDESC 카테고리별 높은 가격순
				Object[] args = { pvo.getCategory(), pvo.getSearchLowPrice(), pvo.getSearchHighPrice() };
				return (ArrayList<ProductVO>) jdbcTemplate.query(SQL_SELECTALL_CATEGORY_PRICEDESC, args, new ProductRowMapper());
				
			}  else if(!pvo.getCategory().equals("all") && pvo.getSort().equals("regiDesc")) {
				// 카테고리가 있고 CATEGORY_REGIDESC 카테고리별 최신순
				Object[] args = { pvo.getCategory(), pvo.getSearchLowPrice(), pvo.getSearchHighPrice() };
				return (ArrayList<ProductVO>) jdbcTemplate.query(SQL_SELECTALL_CATEGORY_REGIDESC, args, new ProductRowMapper());
				
			}  else if(pvo.getCategory().equals("all") && pvo.getSort().equals("sellDesc")) {
				// 카테고리가 없고  sort가 sellDesc 카테고리별 인기순
				System.out.println("	로그: SA pcategory==all sort=sellDesc");
				Object[] args = { pvo.getSearchLowPrice(), pvo.getSearchHighPrice() };
				return (ArrayList<ProductVO>) jdbcTemplate.query(SQL_SELECTALL_POPULAR, args, new ProductRowMapper());
		
			}  else if(pvo.getCategory().equals("all") && pvo.getSort().equals("priceAsc")) {
				// 카테고리가 없고 sort가 PRICEASC 카테고리별 낮은 가격순
				Object[] args = { pvo.getSearchLowPrice(), pvo.getSearchHighPrice() };
				return (ArrayList<ProductVO>) jdbcTemplate.query(SQL_SELECTALL_PRODUCT_ASC, args, new ProductRowMapper());
				
			}  else if(pvo.getCategory().equals("all") && pvo.getSort().equals("priceDesc")) {
				// 카테고리가 없고 sort가 CATEGORY_PRICEDESC 카테고리별 높은 가격순
				Object[] args = { pvo.getSearchLowPrice(), pvo.getSearchHighPrice() };
				return (ArrayList<ProductVO>) jdbcTemplate.query(SQL_SELECTALL_PRODUCT_DESC, args, new ProductRowMapper());
				
			}  else if(pvo.getCategory().equals("all") && pvo.getSort().equals("regiDesc")) {
				// 카테고리가 없고 CATEGORY_REGIDESC 카테고리별 최신순
				System.out.println("	로그: SA pcategory==all sort=regiDesc");
				Object[] args = { pvo.getSearchLowPrice(), pvo.getSearchHighPrice() };
				return (ArrayList<ProductVO>) jdbcTemplate.query(SQL_SELECTALL_NEW, args, new ProductRowMapper());
				
			}
			else if (pvo.getCategory() != null) {
				// 상품 카테고리별 검색
				Object[] args = { pvo.getCategory(), pvo.getSearchLowPrice(), pvo.getSearchHighPrice() };
				return (ArrayList<ProductVO>) jdbcTemplate.query(SQL_SELECTALL_CATEGORY, args, new ProductRowMapper());
				
			} else {
				// 모두 해당 안되면
				return null;
			}
		}
	}
		
		 //SQL문의 where 절에 Image 테이블 TYPE_NO 컬럼이 필요한데, vo에 I_NM만 멤버변수로 가지고 있으면 불러올 수가 없을
		// 것 같아서
		// ImageVO 제작해서 객체화하고 typeNum 불러와서 값 넣음.
//		if(ivo.getTypeNum()==101||ivo.getTypeNum()==102) {
//			Object[] args = { ivo.getTypeNum() };
//			return (ArrayList<ProductVO>) jdbcTemplate.query(SQL_SELECTALL_IMAGE, args, new ProductRowMapper());
//		}
//		return null;
//	}

	// 상품 상세 검색
	public ProductVO selectOne(ProductVO pvo) {
		
		Object[] args = { pvo.getpNum() };
		return jdbcTemplate.queryForObject(SQL_SELECTONE, args, new ProductRowMapper());
	}

	class ProductRowMapper implements RowMapper<ProductVO> {

		@Override
		public ProductVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			System.out.println("	로그: 확인2");
			ProductVO pvo = new ProductVO();
			pvo.setpNum(rs.getInt("P_NO"));
			pvo.setpName(rs.getString("P_NM"));
			pvo.setCategory(rs.getString("P_CATEGORY"));
			pvo.setPrice(rs.getInt("P_PRICE"));
			pvo.setpAmt(rs.getInt("P_AMT"));
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