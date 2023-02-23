package com.wan.nss.biz.board;

public class BoardVO {
	private int boardNum; // 글 번호
	private int memberNum; // 글 작성자
	private String boardTitle; // 글 제목
	private String boardContent; // 글 내용
	private String boardDate; // 글 작성일
	private int boardStatus; // 글 상태 (0 : 정상, 1 : 신고, 2 : 삭제)
	private int boardView; // 조회수

//	vo에만 존재하는 멤버변수들
	private int likeCnt; // 좋아요 수
	private String imageName; // 이미지 이름
	private boolean isChecked; // 해당 회원이 좋아요 했으면 true, 아니면 false
	private int replyCnt; // 댓글 수

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

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(String boardDate) {
		this.boardDate = boardDate;
	}

	public int getBoardStatus() {
		return boardStatus;
	}

	public void setBoardStatus(int boardStatus) {
		this.boardStatus = boardStatus;
	}

	public int getBoardView() {
		return boardView;
	}

	public void setBoardView(int boardView) {
		this.boardView = boardView;
	}

	public int getLikeCnt() {
		return likeCnt;
	}

	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	public int getReplyCnt() {
		return replyCnt;
	}

	public void setReplyCnt(int replyCnt) {
		this.replyCnt = replyCnt;
	}

	@Override
	public String toString() {
		return "BoardVO [boardNum=" + boardNum + ", memberNum=" + memberNum + ", boardTitle=" + boardTitle
				+ ", boardContent=" + boardContent + ", boardDate=" + boardDate + ", boardStatus=" + boardStatus
				+ ", boardView=" + boardView + ", likeCnt=" + likeCnt + ", imageName=" + imageName + ", isChecked="
				+ isChecked + ", replyCnt=" + replyCnt + "]";
	}
}