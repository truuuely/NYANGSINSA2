package com.wan.nss.biz.order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.wan.nss.biz.orderDetail.OrderDetailVO;

@Repository("orderDAO")
public class OrderDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	// 주문 추가
	private final String SQL_INSERT = "INSERT INTO ORDER (M_NO, O_DT, RCV_NM, RCV_PHONE_NO, RCV_ADDRESS, O_PAY) VALUES(?, ?, ?, ?, ?, ?)";
	// 주문 전체 목록
	private final String SQL_SELECTALL = "SELECT * FROM ORDER ORDER BY O_NO DESC ";
	// 주문 번호 검색
	private final String SQL_SEARCH_ORDER_NUM = "SELECT * FROM ORDER WHERE O_NO LIKE CONCAT('%',?,'%')";
	// 주문 내역 보기
	private final String SQL_SELECTALL_ORDER = "SELECT * FROM ORDER WHERE M_NO =? ORDER BY O_NO DESC ";
	// 주문 총 가격
	private final String SQL_SELECT_TOTAL_PRICE = "	SELECT SUM(PRODUCT.PRICE*((100-PRODUCT.DC_PERCENT)/100)*ORDER_DETAIL.OD_CNT) AS TOTAL "
			+ " FROM ORDER_DETAIL od INNER JOIN PRODUCT p ON od.P_NO = p.P_NO WHERE od.O_NO = ?";
	// 주문 삭제
	private final String SQL_DELETE = "DELETE FROM ORDER WHERE O_NO=? ";

	public boolean insert(OrderVO vo) {
		jdbcTemplate.update(SQL_INSERT, vo.getoNum(), vo.getoDate(), vo.getRcvName(), vo.getRcvPhoneNum(),
				vo.getRcvAddress(), vo.getoPay());
		return true;
	}

	public boolean update(OrderVO vo) {
		jdbcTemplate.update(SQL_INSERT, vo.getoNum(), vo.getoDate(), vo.getRcvName(), vo.getRcvPhoneNum(),
				vo.getRcvAddress(), vo.getoPay());
		return true;
	}

	public boolean delete(OrderVO vo) {
		jdbcTemplate.update(SQL_DELETE, vo.getoNum());
		return true;
	}

	public OrderVO selectOne(OrderVO vo) {
		OrderDetailVO odvo = new OrderDetailVO();
		Object[] args = { odvo.getoNum() };
		return jdbcTemplate.queryForObject(SQL_SELECT_TOTAL_PRICE, args, new OrderRowMapper());
	}

	public ArrayList<OrderVO> selectAll(OrderVO vo) {
		if (vo.getmNum() >= 0) {
			Object[] args = { vo.getmNum() };
			return (ArrayList<OrderVO>) jdbcTemplate.query(SQL_SELECTALL_ORDER, args, new OrderRowMapper());
		} else if (vo.getoNum() >= 0) {
			Object[] args = { vo.getoNum() };
			return (ArrayList<OrderVO>) jdbcTemplate.query(SQL_SEARCH_ORDER_NUM, args, new OrderRowMapper());
		} else {
			return (ArrayList<OrderVO>) jdbcTemplate.query(SQL_SELECTALL, new OrderRowMapper());
		}
	}

	class OrderRowMapper implements RowMapper<OrderVO> { // 스프링에서 제공해주는

		@Override
		public OrderVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			// 오버라이딩 강제
			OrderVO vo = new OrderVO();
			vo.setmNum(rs.getInt("M_NO"));
			vo.setoDate(rs.getString("O_DT"));
			vo.setoNum(rs.getInt("O_NO"));
			vo.setoPay(rs.getString("O_PAY"));
			vo.setRcvAddress(rs.getString("RCV_ADDRESS"));
			vo.setRcvName(rs.getString("RCV_NM"));
			vo.setRcvPhoneNum(rs.getString("RCV_PHONE_NO"));
			return vo;

		}
	}
}
