package com.wan.nss.biz.reply;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("replyDAO")
public class ReplyDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	// 댓글 작성 - 글 번호, 댓글 작성자 id, 내용 필요
	private final String INSERT_REPLY = "INSERT INTO REPLY (B_NO, M_NO, RE_CONTENT, RE_STEP) VALUES "
			+ "(?, (SELECT M_NO FROM MEMBER WHERE M_ID = ?), ?, 2)";

	// 대댓글 작성 - 글 번호, 대댓글 작성자 id, 부모 댓글 pk, 내용 필요
	private final String INSERT_REREPLY = "INSERT INTO REPLY (B_NO, M_NO, PARENT_NO, RE_CONTENT, RE_STEP) VALUES "
			+ "(?, (SELECT M_NO FROM MEMBER WHERE M_ID = ?), (SELECT M_NO FROM MEMBER WHERE M_ID = ?), ?, 3)";

	// 댓글 가져오기 - 글 번호 필요
	private final String SELECT_ALL_REPLY = "SELECT	RE_NO, B_NO, M_NO, PARENT_NO, RE_DATE, " + "	CASE"
			+ "		WHEN STATUS = 2" + "		THEN '*** 관리자가 검토중인 댓글입니다. ***'" + "		WHEN STATUS = 3"
			+ "		THEN '*** 해당 댓글은 삭제된 댓글입니다. ***'" + "		ELSE RE_CONTENT " + "	END AS RE_CONTENT "
			+ "	, RE_STEP " + " FROM REPLY WHERE B_NO = ? AND RE_STEP = 2 " + " ORDER BY RE_NO";

	// 대댓글 가져오기 - 글 번호, 대댓글 작성자 id 필요
	private final String SELECT_ALL_REREPLY = "SELECT RE_NO, B_NO, M_NO, PARENT_NO, RE_DATE, " + "	CASE"
			+ "		WHEN STATUS = 2" + "		THEN '*** 관리자가 검토중인 댓글입니다. ***'" + "		WHEN STATUS = 3"
			+ "		THEN '*** 해당 댓글은 삭제된 댓글입니다. ***'" + "		ELSE RE_CONTENT " + "	END AS RE_CONTENT "
			+ "	, RE_STEP "
			+ "FROM REPLY WHERE B_NO = ? AND PARENT_NO = ? AND RE_STEP = 3 "
			+ "ORDER BY RE_NO";

	private final String SELECT_ONE = "SELECT * FROM REPLY WHERE RE_NO = ?";

	// 관리자 : 댓글 상태 수정 (1: 정상, 2: 신고, 3:삭제)
	private final String UPDATE = "UPDATE REPLY SET STATUS = ? WHERE RE_NO = ?";

	// 댓글 삭제
	private final String DELETE = "UPDATE REPLY SET STATUS = 3 WHERE RE_NO = ?";

	public boolean insert(ReplyVO vo) {
		int res = 0;
		if (vo.getParentNum() == 0) { // 댓글 작성
			res = jdbcTemplate.update(INSERT_REPLY, vo.getBoardNum(), vo.getMemberId(), vo.getReplyContent());
		} else {
			res = jdbcTemplate.update(INSERT_REREPLY, vo.getBoardNum(), vo.getMemberId(), vo.getParentNum(),
					vo.getReplyContent());
		}

		if (res < 1) {
			return false;
		}
		return true;
	}

	public ArrayList<ReplySet> selectAll(ReplyVO vo) {
		ArrayList<ReplySet> rsList = new ArrayList<>(); // 댓글, 대댓글 모음
		
		ArrayList<ReplyVO> rDatas = (ArrayList<ReplyVO>) jdbcTemplate.query(SELECT_ALL_REPLY, new ReplyRowMapper(),
				vo.getBoardNum()); // 댓글 set
		for (int i = 0; i < rDatas.size(); i++) { // 글에 대한 댓글들 수만큼
			ReplySet rs = new ReplySet();
			rs.setReply(rDatas.get(i));
			 // 댓글에 대한 대댓글 set
			ArrayList<ReplyVO> rrDatas = (ArrayList<ReplyVO>) jdbcTemplate.query(SELECT_ALL_REREPLY, new ReplyRowMapper(), vo.getBoardNum(), vo.getParentNum());
			rs.setReplyList(rrDatas);
			
			rsList.add(rs);
		}

		return rsList;
	}

	public ReplyVO selectOne(ReplyVO vo) {
		return jdbcTemplate.queryForObject(SELECT_ONE, new ReplyRowMapper(), vo.getReplyNum());
	}

	public boolean update(ReplyVO vo) {
		if (jdbcTemplate.update(UPDATE, vo.getReplyStatus(), vo.getReplyNum()) < 1) {
			return false;
		}
		return true;
	}

	public boolean delete(ReplyVO vo) {
		if (jdbcTemplate.update(DELETE, vo.getReplyNum()) < 1) {
			return false;
		}
		return true;
	}
}

class ReplyRowMapper implements RowMapper<ReplyVO> {

	@Override
	public ReplyVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ReplyVO data = new ReplyVO();
		data.setReplyNum(rs.getInt("RE_NO"));
		data.setBoardNum(rs.getInt("B_NO"));
		data.setMemberNum(rs.getInt("M_NO"));
		data.setReplyDate(rs.getString("RE_DATE"));
		data.setReplyContent(rs.getString("RE_CONTENT"));
		data.setReplyStatus(rs.getInt("STATUS"));
		data.setReplyStep(rs.getInt("RE_STEP"));
		data.setParentNum(rs.getInt("PARENT_NO"));
		return data;
	}

}
