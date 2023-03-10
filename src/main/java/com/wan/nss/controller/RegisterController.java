package com.wan.nss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wan.nss.biz.member.MemberService;
import com.wan.nss.biz.member.MemberVO;

@Controller
public class RegisterController {
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="/checkDuplication.do", method=RequestMethod.POST)
	public String isDuplicated(MemberVO mvo) {
		
		if(memberService.selectOne(mvo) == null) {
			// 중복 없음 !
			// 아이디 만들 수 있음
			System.out.println("중복 없음");
			System.out.println("id = " + mvo.getUserId() + ", phone= " + mvo.getPhoneNum());
			
			return "1";
		} else {
			return "0";
		}
	}
}
