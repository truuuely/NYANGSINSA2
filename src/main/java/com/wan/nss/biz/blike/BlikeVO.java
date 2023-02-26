package com.wan.nss.biz.blike;

public class BlikeVO {
	private int likeNum; // 좋아요 PK
	private int boardNum; // 글 번호
	private int memberNum; // 좋아요 누른 회원의 회원 번호.
	
	// vo에만 존재하는 멤버변수
	private String memberId; // 좋아요 누른 회원의 ID

	public int getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(int likeNum) {
		this.likeNum = likeNum;
	}

	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}

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

	@Override
	public String toString() {
		return "BlikeVO [likeNum=" + likeNum + ", boardNum=" + boardNum + ", memberNum=" + memberNum + ", memberId="
				+ memberId + "]";
	}
}
