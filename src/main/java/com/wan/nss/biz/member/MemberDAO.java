package com.wan.nss.biz.member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring.biz.board.BoardVO;

@Repository("memberDAO")
public class MemberDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// 회원가입
	private final String SQL_INSERT = "INSERT INTO MEMBER (M_ID, M_PW, M_NM, CAT_NM, PHONE_NO, POST_NO, ADDRESS1, ADDRESS2) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	// 회원 아이디, 비밀번호 검색
	private final String SQL_SELECTONE = "SELECT * FROM MEMBER WHERE M_ID = ? AND M_PW = ?";
	// 회원 아이디 중복 검사 할 때 필요한 것
	private final String SQL_SELECTONE_ID = "SELECT M_ID FROM MEMBER WHERE M_ID = ? "; 
	// 핸드폰 번호 중복 검사할 때 필요한 것 
	private final String SQL_SELECTONE_PHONE = "SELECT PHONE_NO FROM MEMBER WHERE PHONE_NO = ?"; 																							
	// private final String SQL_SELECTONE_MYPAGE = "SELECT * FROM MEMBER WHERE M_ID = ? ";  // 회원(작성자) 아이디 검색
	// 회원 전체 목록 보기
	private final String SQL_SELECTALL = "SELECT * FROM MEMBER";
	// 회원 (작성자) 아이디 검색
	private final String SQL_SELECTALL_ID = "SELECT * FROM MEMBER WHERE M_ID LIKE CONCAT ('%',?,'%')"; 
	// 아이디 찾기 할 때 필요한 폰 번호 입력																							
	private final String SQL_SELECTONE_FIND_ID = "SELECT * FROM MEMBER WHERE PHONE_NO= ?"; 
	// 비밀번호 찾기 할 때 필요
	private final String SQL_SELECTONE_FIND_PW = "SELECT * FROM MEMBER WHERE M_ID = ? AND M_NM = ? AND PHONE_NO= ? "; 
	// 회원정보 업데이트
	private final String SQL_UPDATE = "UPDATE MEMBER SET M_PW = ?, M_NM = ?, CAT_NM = ?, PHONE_NO = ?, POST_NO = ? ADDRESS1 = ?, ADDRESS2 = ? WHERE M_NO = ?";
	// 회원 비밀번호 수정
	private final String SQL_UPDATE_PW = "UPDATE MEMBER SET M_PW = ? WHERE M_ID=?"; 
	// 회원 삭제
	private final String SQL_DELETE = "DELETE FROM MEMBER WHERE M_NO = ?"; 

	public boolean insert(MemberVO vo) { // 회원가입
		jdbcTemplate.update(SQL_INSERT, vo.getMemberId(), vo.getMemberPw(), vo.getCatName(), vo.getPhoneNum(),
				vo.getPostNum(), vo.getAddress1(), vo.getAddress2());
		return true;
	}

	public boolean update(MemberVO vo) { // 회원 정보 변경
		jdbcTemplate.update(SQL_UPDATE, vo.getMemberPw(), vo.getMemberName(), vo.getCatName(), vo.getPhoneNum(),
				vo.getPostNum(), vo.getAddress1(), vo.getAddress2(), vo.getMemberNum());
		return true;
	}

	public boolean delete(MemberVO vo) { // 회원탈퇴, 회원 삭제
		jdbcTemplate.update(SQL_DELETE, vo.getMemberNum());
		return true;
	}

	public MemberVO selectOne(MemberVO vo) { // 중복된 아이디가 있으면 true / 없으면 false
		if (vo.getMemberId() != null && vo.getMemberPw() != null) {
			// 3. 로그인 할 때 아이디랑 패스워드 둘 다 맞는지 확인 / memberId, memberPw 둘 다 존재
			// SELECETONE
		} else if (vo.getMemberId() != null) {
			// 1. 회원가입 할 때 아이디 중복 확인 / vo에 memberId만 존재
			// SELECETONE_ID
		} else if (vo.getPhoneNum() != null) {
			// 2. 회원가입 할 때 핸드폰 번호 확인 / vo에 phoneNum만 존재
			// SELECETONE_PHONE
		}

	}

	public ArrayList<MemberVO> selectAll(MemberVO vo) {

		return datas;
	}

	class MemberRowMapper implements RowMapper<MemberVO> { // 스프링에서 제공해주는  

		@Override
		public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException { 
			//오버라이딩 강제 
			MemberVO data=new MemberVO();
			data.setBid(rs.getInt("BID"));
			data.setContent(rs.getString("CONTENT"));
			data.setTitle(rs.getString("TITLE"));
			data.setWriter(rs.getString("WRITER"));
			return data;
		}
   
}
