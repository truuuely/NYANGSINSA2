package com.wan.nss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wan.nss.biz.board.BoardService;
import com.wan.nss.biz.board.BoardVO;
import com.wan.nss.biz.member.MemberService;
import com.wan.nss.biz.member.MemberVO;
import com.wan.nss.biz.reply.ReplyService;
import com.wan.nss.biz.reply.ReplyVO;
import com.wan.nss.biz.report.ReportService;
import com.wan.nss.biz.report.ReportVO;

@Controller
public class ReportController {

	@Autowired
	private ReportService reportService;
	@Autowired
	private BoardService boardService;
	@Autowired
	private ReplyService replyService;
	@Autowired
	private MemberService memberService;

	// 신고하기
	@RequestMapping(value = "/insertReport.do")
	public String insertReport(ReportVO rpvo) {
		System.out.println("insertReport.do 진입");
		// targetNum, reportStep, userID, reportContent
		// 폼 안에서 체크된(활성화 된) 값만 오므로 처리하면 됨

		// setting된 input 확인
		System.out.println("targetNum: " + rpvo.getTargetNum());
		System.out.println("reportStep: " + rpvo.getReportStep());
		System.out.println("userId: " + rpvo.getUserId());
		System.out.println("reportContent: " + rpvo.getReportContent());

		reportService.insert(rpvo);

		return "board_detail.jsp";
	}

	// (관리자) Report 게시글 신고 처리
	@RequestMapping(value = "/updateReport.do")
	public String updateReport(ReportVO rpvo, MemberVO mvo) {
		System.out.println("updateReport.do 진입");
		// 넘겨받는 것
		// : targetNum, reportNum, userId(vo), reporterId(vo), reportStep, proStatus
		// warnCnt+1

		if (rpvo.getProcStatus().equals("cancel")) { // 신고 취소, 신고한 사람에게 경고 +1
			mvo.setUserId(rpvo.getReporterId());
			memberService.update(mvo);
		} else if (rpvo.getProcStatus().equals("ok")) { // 신고 처리, 신고당한 사람에게 경고 +1
			mvo.setUserId(rpvo.getUserId());
			memberService.update(mvo);

			if (rpvo.getReportStep() == 1) { // 글일 경우
				BoardVO bvo = new BoardVO();
				bvo.setBoardNum(rpvo.getTargetNum());
				bvo.setBoardStatus(3);
				bvo.setSearchCondition("changeStatus");
				boardService.update(bvo);
			} else if (rpvo.getReportStep() == 2 || rpvo.getReportStep() == 3) { // 댓글일 경우
				ReplyVO rvo = new ReplyVO();
				rvo.setReplyStatus(3);
				rvo.setReplyNum(rpvo.getTargetNum());
				replyService.update(rvo);
			}
			// 신고 완료
		}
		rpvo.setReportStat(2);
		reportService.update(rpvo);
		return "report_manage.jsp";
	}

}
