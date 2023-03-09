package com.wan.nss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wan.nss.biz.member.MemberService;
import com.wan.nss.biz.member.MemberVO;

@Controller
public class RegisterController {
	@Autowired
	private MemberService memberService;
	
	@ResponseBody
	@RequestMapping(value="/checkDuplication.do", method=RequestMethod.POST)
	public String isDuplicated(MemberVO mvo) {
		if(memberService.selectOne(mvo) == null) {
			// 중복 없음 !
			// 아이디 만들 수 있음
			return "1";
		} else {
			return "0";
		}
	}
}
