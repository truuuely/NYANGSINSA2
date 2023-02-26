package com.wan.nss.biz.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDAO memberDAO;

	@Override
	public boolean insertMember(MemberVO vo) {
		return memberDAO.insert(vo);
	}

	@Override
	public boolean updateMember(MemberVO vo) {
		return memberDAO.update(vo);
	}

	@Override
	public boolean deleteMember(MemberVO vo) {
		return memberDAO.delete(vo);
	}

	@Override
	public MemberVO selectOne(MemberVO vo) {
		return memberDAO.selectOne(vo);
	}

}
