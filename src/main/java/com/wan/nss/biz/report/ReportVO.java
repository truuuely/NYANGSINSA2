package com.wan.nss.biz.report;

public class ReportVO {
	private int reportNum; // 신고 PK
	private int targetNum; // 글/댓글의 PK
	private int reportStep; // 글/댓글 구분 번호 (1 : 게시글, 2 : 댓글, 3 : 대댓글)
	private int memberNum; // 신고 당한 회원의 회원 번호
	private String reportDate; // 신고 일자
	private String reportContent; // 신고 내용
	private int status; // 신고 상태 (1: 정상, 2: 신고, 3: 삭제)
	
	public int getReportNum() {
		return reportNum;
	}
	public void setReportNum(int reportNum) {
		this.reportNum = reportNum;
	}
	public int getTargetNum() {
		return targetNum;
	}
	public void setTargetNum(int targetNum) {
		this.targetNum = targetNum;
	}
	public int getReportStep() {
		return reportStep;
	}
	public void setReportStep(int reportStep) {
		this.reportStep = reportStep;
	}
	public int getMemberNum() {
		return memberNum;
	}
	public void setMemberNum(int memberNum) {
		this.memberNum = memberNum;
	}
	public String getReportDate() {
		return reportDate;
	}
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
	public String getReportContent() {
		return reportContent;
	}
	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ReportVO [reportNum=" + reportNum + ", targetNum=" + targetNum + ", reportStep=" + reportStep
				+ ", memberNum=" + memberNum + ", reportDate=" + reportDate + ", reportContent=" + reportContent
				+ ", status=" + status + "]";
	}
}
