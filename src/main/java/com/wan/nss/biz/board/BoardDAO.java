package com.wan.nss.biz.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("boardDAO")
public class BoardDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	// 글 작성
	private final String INSERT = "INSERT INTO BOARD (M_NO, B_TITLE, B_CONTENT) VALUES((SELECT M_NO FROM MEMBER WHERE M_ID = ?), ?, ?)";
	// PK 오름차순으로 글 보기 (삭제된 글 제외)
	private final String SELECT_ALL = "SELECT * FROM BOARD WHERE STATUS != 2 ORDER BY B_NO ASC";
	
	private final String SELECT_ONE = "SELECT * FROM BOARD ";
	// 관리자 모드 : 게시글 상태 변경 (0 : 정상, 1 : 신고, 2: 삭제)
	private final String UPDATE = "UPDATE BOARD SET STATUS = ? WHERE B_NO = ?;";
	
	private final String DELETE = "UPDATE BOARD SET STATUS = 2 WHERE B_NO = ?";

	public boolean insert(BoardVO vo) {
		/* TODO : 조건문 추가하기*/
		jdbcTemplate.update(INSERT, vo.getMemberNum(), vo.getBoardTitle(), vo.getBoardContent());
		return true;
	}
}
