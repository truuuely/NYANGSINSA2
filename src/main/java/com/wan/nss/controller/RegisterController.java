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
//	@Autowired
//	private SmsService smsService;
	
	// 아이디, 전화번호 중복 확인
	@ResponseBody
	@RequestMapping(value="/checkDuplication.do", method=RequestMethod.POST)
	public String isDuplicated(MemberVO mvo) {
		
		System.out.println("checkDuplication.do 진입");

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
	
//	// 회원가입시 문자 인증 번호 발송
//	@RequestMapping(value="/sms.do")
//	public String sendSms(MemberVO mvo) {
//		
//		System.out.println("sms.do 진입");
//		
//		System.out.println(svo.getUserPhone()); //사용자 전화번호 가져오기
//		
//		int randNum = smsService.sns(svo);  //sns메서드 통해 randNum값 리턴받아옴.
//		System.out.println(randNum);
//		
//		//받아온 randNum값을 다시 ajax으로 보낸다 ->  success: function(randNum)  
//		return randNum+"";
//	}
//	
//	// 회원가입시 문자 인증 번호 확인
//	@RequestMapping(value="/checkSms.do")
//	public String checkSms(SmsVO svo) {
//		
//		System.out.println("checkSms.do 진입");
//		
//		System.out.println("로그:" + svo.getRandNum());
//		System.out.println("로그:" + svo.getChecknum());
//
//		if (svo.getRandNum().equals(svo.getChecknum())) { // 인증번호와 사용자입력값 비교
//
//			return "1"; // => result == "1" 을 뜻함
//			System.out.println("인증번호 일치함");
//			
//		} else {
//			
//			return "-1"; // => result == "-1" 을 뜻함
//			System.out.println("인증번호 불일치");
//		}
//		
//	}
	
}
