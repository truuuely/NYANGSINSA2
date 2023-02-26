package com.wan.nss.biz.report;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("reportDAO")
public class ReportDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/*
	 * C : 게시글, 댓글/대댓글 신고. TARGET_NO 에는 게시글 PK 혹은 댓글 PK가 들어가야 함.
	 * 	 RP_STEP = 0(글), 1(댓글), 2(대댓글)
	 */
	private final String INSERT = "INSERT INTO REPORT (TARGET_NO, RP_STEP, M_NO, RP_CONTENT) VALUES (?, ?, (SELECT M_NO FROM MEMBER WHERE M_ID = ?), ?)";

	// R : Report 전체 보기 (삭제된 신고글 제외)
	private final String SELECT_ALL = "SELECT * FROM REPORT WHERE STATUS != 3 ORDER BY RP_NO DESC";

	// R : Report 상세보기
	private final String SELECT_ONE = "SELECT * FROM REPORT WHERE RP_NO = ?";

	// U : Report 신고 취소(정상)
	private final String UPDATE = "UPDATE REPORT SET STATUS = 1 WHERE RP_NO = ?";

	// D : Report 삭제 처리
	private final String DELETE = "UPDATE REPORT SET STATUS = 3 WHERE RP_NO = ?";

	public boolean insert(ReportVO vo) {
		if (jdbcTemplate.update(INSERT, vo.getTargetNum(), vo.getReportStep(), vo.getMemberId(),
				vo.getReportContent()) < 1) {
			return false;
		}
		return true;
	}

	public ArrayList<ReportVO> selectAll(ReportVO vo) {
		return (ArrayList<ReportVO>) jdbcTemplate.query(SELECT_ALL, new ReportRowMapper());
	}

	public ReportVO selectOne(ReportVO vo) {
		return jdbcTemplate.queryForObject(SELECT_ONE, new ReportRowMapper(), vo.getReportNum());
	}
	
	public boolean update(ReportVO vo) {
		if (jdbcTemplate.update(UPDATE, vo.getReportNum()) < 1) {
			return false;
		}
		return true;
	}

	public boolean delete(ReportVO vo) {
		if (jdbcTemplate.update(DELETE, vo.getReportNum()) < 1) {
			return false;
		}
		return true;
	}
}

class ReportRowMapper implements RowMapper<ReportVO> {
	@Override
	public ReportVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ReportVO data = new ReportVO();
		data.setReportNum(rs.getInt("RP_NO"));
		data.setTargetNum(rs.getInt("TARGET_NO"));
		data.setReportStep(rs.getInt("RP_STEP"));
		data.setMemberNum(rs.getInt("M_NO"));
		data.setReportDate(rs.getString("RP_DATE"));
		data.setReportContent(rs.getString("RP_CONTENT"));
		data.setStatus(rs.getInt("STATUS"));
		return data;
	}

}
