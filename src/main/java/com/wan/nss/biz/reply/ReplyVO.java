package com.wan.nss.biz.reply;

public class ReplyVO {
	private int replyNum; // 댓글 번호
	private int boardNum; // 글 번호
	private int memberNum; // 댓글 작성자 번호
	private String replyDate; // 댓글 작성일
	private String replyContent; // 댓글 내용
	private int replyStatus; // 댓글 상태
	private int replyStep; // 댓글의 계층
	private int parentNum; // 부모 댓글의 댓글 번호

	private String memberId; // 댓글 작성자 id
	private String parentId; // 부모 댓글 작성자 id

	public int getReplyNum() {
		return replyNum;
	}

	public void setReplyNum(int replyNum) {
		this.replyNum = replyNum;
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

	public String getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(String replyDate) {
		this.replyDate = replyDate;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public int getReplyStatus() {
		return replyStatus;
	}

	public void setReplyStatus(int replyStatus) {
		this.replyStatus = replyStatus;
	}

	public int getReplyStep() {
		return replyStep;
	}

	public void setReplyStep(int replyStep) {
		this.replyStep = replyStep;
	}

	public int getParentNum() {
		return parentNum;
	}

	public void setParentNum(int parentNum) {
		this.parentNum = parentNum;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		return "ReplyVO [replyNum=" + replyNum + ", boardNum=" + boardNum + ", memberNum=" + memberNum + ", replyDate="
				+ replyDate + ", replyContent=" + replyContent + ", replyStatus=" + replyStatus + ", replyStep="
				+ replyStep + ", parentNum=" + parentNum + ", memberId=" + memberId + ", parentId=" + parentId + "]";
	}
}
