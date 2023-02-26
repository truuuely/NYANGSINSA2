package com.wan.nss.biz.member;

public class MemberVO {
	private int memberNum; // 회원 번호
	private String memberId; // 회원 아이디
	private String memberPw; // 회원 비밀번호
	private String memberName; // 회원 이름
	private String catName; // 고앵쓰 이름 
	private String phoneNum; // 전화번호
	private String role; // 등급
	private int warnCnt; // 경고 횟수 
	private String postNum; // 우편번호
	private String address1; // 기본 주소
	private String address2; // 상세 주소
	
	public int getMemberNum() {
		return memberNum;
	}
	public void setMemberNum(int memberNum) {
		this.memberNum = memberNum;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getWarnCnt() {
		return warnCnt;
	}
	public void setWarnCnt(int warnCnt) {
		this.warnCnt = warnCnt;
	}
	public String getPostNum() {
		return postNum;
	}
	public void setPostNum(String postNum) {
		this.postNum = postNum;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	@Override
	public String toString() {
		return "MemberVO [memberNum=" + memberNum + ", memberId=" + memberId + ", memberPw=" + memberPw
				+ ", memberName=" + memberName + ", catName=" + catName + ", phoneNum=" + phoneNum + ", role=" + role
				+ ", warnCnt=" + warnCnt + ", postNum=" + postNum + ", address1=" + address1 + ", address2=" + address2
				+ "]";
	}



}