package com.wan.nss.biz.orderdetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.wan.nss.biz.product.ProductVO;

@Repository("orderdetailDAO")
public class OrderDetailDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// 주문 상세 추가
	private final String SQL_INSERT = "INSERT INTO ORDER_DETAIL (O_NO, P_NO, OD_CNT, OD_PRICE) VALUES(?, ?, ?, ?)";
	// 주문 전체 목록 출력
	private final String SQL_SELECTALL = "SELECT ORDER_DETAIL.O_NO, ORDER_DETAIL.OD_NO, PRODUCT.P_NM, ORDER_DETAIL.OD_CNT, SUM(PRODUCT.PRICE * ORDER_DETAIL.OD_CNT) AS TOTAL FROM PRODUCT p INNER JOIN ORDER_DETAIL od ON p.P_NO = od.P_NO GROUP BY p.P_NM, od.OD_CNT, od.O_NO, od.OD_NO ORDER BY od.O_NO DESC";

	private final String SQL_SELECTALL_ONUM = "SELECT ORDER_DETAIL.P_NO, ORDER_DETAIL.O_NO, PRODUCT.P_NM, ORDER_DETAIL.OD_CNT, PRODUCT.P_IMG_URL, SUM(PRODUCT.PRICE*ORDER_DETAIL.OD_CNT) AS TOTAL FROM PRODUCT p INNER JOIN ORDER_DETAIL od ON p.P_NO = od.P_NO AND od.O_NO = ? GROUP BY od.Pp_NO, p.P_NM, od.OD_CNT, od.O_NO";

	private final String SQL_SELECTONE_CATEGORY_CNT_SUM = "SELECT COUNT(ORDER_DETAIL.OD_NO) AS CNT, SUM(PRODUCT.PRICE * ORDER_DETAIL.OD_CNT) AS SUM FROM ORDER_DETAIL od INNER JOIN PRODUCT p ON p.P_NO = od.P_NO WHERE p.CATEGORY = ?";

	public boolean insert(OrderDetailVO odvo) { // 회원가입
		jdbcTemplate.update(SQL_INSERT, odvo.getoNum(), odvo.getpNum(), odvo.getOdCnt(), odvo.getOdPrice());
		return true;
	}

	public ArrayList<OrderDetailVO> selectAll(OrderDetailVO odvo) {
		ProductVO pvo = new ProductVO();
		// 상품 이름으로 검색
		if (odvo.getOdNum() >= 0) {
			Object[] args = { odvo.getOdNum() };
			return (ArrayList<OrderDetailVO>) jdbcTemplate.query(SQL_SELECTALL_ONUM, args, new OrderDetailRowMapper());
		} else if (pvo.getpCategory() != null) {
			Object[] args = { pvo.getpCategory() };
			return (ArrayList<OrderDetailVO>) jdbcTemplate.query(SQL_SELECTONE_CATEGORY_CNT_SUM, args, new OrderDetailRowMapper());
		} else {
			return (ArrayList<OrderDetailVO>) jdbcTemplate.query(SQL_SELECTALL, new OrderDetailRowMapper());
		}
	}

	class OrderDetailRowMapper implements RowMapper<OrderDetailVO> {

		@Override
		public OrderDetailVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			OrderDetailVO odvo = new OrderDetailVO();
			odvo.setOdNum(rs.getInt("OD_NO"));
			odvo.setoNum(rs.getInt("O_NO"));
			odvo.setpNum(rs.getInt("P_NO"));
			odvo.setOdCnt(rs.getInt("OD_CNT"));
			odvo.setOdPrice(rs.getInt("OD_PRICE"));

			return odvo;
		}
	}
}
