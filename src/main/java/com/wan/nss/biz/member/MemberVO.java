package com.wan.nss.biz.member;

public class MemberVO {
	private int memberNum;
	private String memberId;
	private String memberPw;
	private String memberName;
	private String catName;
	private String phoneNum;
	private String role;
	private int warnCnt;
	private String postNum;
	private String address1;
	private String address2;
	
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