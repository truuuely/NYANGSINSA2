package com.wan.nss.biz.review;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("reviewDAO")
public class ReviewDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// 리뷰 작성
	private final String SQL_INSERT = "INSERT INTO REVIEW (P_NO, R_WRITER, R_CONTENT, R_DT, R_RATE) VALUES(100, ?, ?, ?, ?)";
	
	// 리뷰 업데이트
	private final String SQL_UPDATE = "UPDATE REVIEW SET P_NO=?, R_WRITER=?, R_CONTENT=? WHERE R_NO= ?";
	
	// 리뷰 삭제
	private final String SQL_DELETE = "DELETE FROM REVIEW WHERE R_NO=?";
	
	// 리뷰 전체 보기
	private final String SQL_SELECTALL = "SELECT * FROM REVIEW ORDER BY R_NO DESC";
	
	// 제품 번호에 대한 리뷰 보기
	private final String SQL_SELECTALL_PRODUCT = "SELECT * FROM REVIEW WHERE P_NO=? ORDER BY R_NO DESC";
	
	// 내가 작성한 리뷰 보기
	private final String SQL_SELECTALL_USER = "SELECT * FROM REVIEW WHERE M_NO=? ORDER BY R_NO DESC";

	// 리뷰 검색
//	private final String SQL_SELECTALL_SEARCH = "SELECT * FROM REVIEW WHERE M_NO LIKE CONCAT('%',?,'%')";

	public boolean insert(ReviewVO rvo) { // 리뷰 작성
		jdbcTemplate.update(SQL_INSERT, rvo.getpNum(), rvo.getrWriter(), rvo.getrContent(), rvo.getrDate(),
				rvo.getrRate());
		return true;
	}

	public boolean update(ReviewVO rvo) { // 리뷰 업데이트
		jdbcTemplate.update(SQL_UPDATE, rvo.getpNum(), rvo.getrWriter(), rvo.getrContent(), rvo.getrNum());
		return true;
	}

	public boolean delete(ReviewVO rvo) { // 리뷰 삭제
		jdbcTemplate.update(SQL_DELETE, rvo.getrNum());
		return true;
	}

	public ArrayList<ReviewVO> selectAll(ReviewVO rvo) {
		if (rvo.getrSearchCondition() == null) { 
			// 전체 리뷰 보기
			return (ArrayList<ReviewVO>) jdbcTemplate.query(SQL_SELECTALL, new ReviewRowMapper());
			
	} else if (rvo.getrSearchCondition().equals("pNum")) {
			// 제품 번호 리뷰 보기 
			Object [] args = { rvo.getpNum() };
			return (ArrayList<ReviewVO>) jdbcTemplate.query(SQL_SELECTALL_PRODUCT, args, new ReviewRowMapper());
			
	} else if (rvo.getrSearchCondition().equals("rWriter")) {
		// 내가 쓴 리뷰 보기 
		Object [] args = { rvo.getrWriter() };
		return (ArrayList<ReviewVO>) jdbcTemplate.query(SQL_SELECTALL_USER, args, new ReviewRowMapper());
	}
		return null;
	}

	class ReviewRowMapper implements RowMapper<ReviewVO> {

		@Override
		public ReviewVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			ReviewVO rvo = new ReviewVO();
			rvo.setpNum(rs.getInt("P_NO"));
			rvo.setrNum(rs.getInt("R_NO"));
			rvo.setrWriter(rs.getInt("M_NO"));
			rvo.setrContent(rs.getString("R_CONTENT"));
			rvo.setrDate(rs.getString("DATE"));
			rvo.setrRate(rs.getInt("R_RATE"));

			return rvo;
		}
	}
}
