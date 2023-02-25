package com.wan.nss.biz.review;

public class ReviewVO {
	private int rNum;
	private int pNum;
	private int memberNum;
	private String rContent;
	private String rDate;
	private int rRate;
	private String rSearchCondition;
	
	public int getrNum() {
		return rNum;
	}
	public void setrNum(int rNum) {
		this.rNum = rNum;
	}
	public int getpNum() {
		return pNum;
	}
	public void setpNum(int pNum) {
		this.pNum = pNum;
	}
	public int getmemberNum() {
		return memberNum;
	}
	public void setmemberNum(int memberNum) {
		this.memberNum = memberNum;
	}
	public String getrContent() {
		return rContent;
	}
	public void setrContent(String rContent) {
		this.rContent = rContent;
	}
	public String getrDate() {
		return rDate;
	}
	public void setrDate(String rDate) {
		this.rDate = rDate;
	}
	public int getrRate() {
		return rRate;
	}
	public void setrRate(int rRate) {
		this.rRate = rRate;
	}
	public String getrSearchCondition() {
		return rSearchCondition;
	}
	public void setrSearchCondition(String rSearchCondition) {
		this.rSearchCondition = rSearchCondition;
	}
	
	@Override
	public String toString() {
		return "ReviewVO [rNum=" + rNum + ", pNum=" + pNum + ", rWriter=" + memberNum + ", rContent=" + rContent
				+ ", rDate=" + rDate + ", rRate=" + rRate + ", rSearchCondition=" + rSearchCondition + "]";
	}
	
	
	
}
