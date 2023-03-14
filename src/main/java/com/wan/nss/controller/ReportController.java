package com.wan.nss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wan.nss.biz.board.BoardService;
import com.wan.nss.biz.board.BoardVO;
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
	
	// ()
	@RequestMapping(value = "/insertReport")
	public String insertReport() {
		
	}

	// (관리자) 신고글 관리 페이지 이동
	@RequestMapping(value = "/reportManageView.do")
	public String reportManageView(ReportVO rpVo, Model model) {
		model.addAttribute("userId", rvo.getUserId()); // 멤버 ID
		model.addAttribute("reporterId",rvo.getReporterId()); // 신고자 ID
		model.addAttribute("rpList", reportService.selectAll(rvo)); // 나머지 정보등 꺼내쓸 수 있는 곳: 여기서 맞게 꺼내쓰세요
		return "report_manage.jsp";
	}

//	// (관리자) 신고 게시글 상세보기 페이지 이동 (확실하면 주석처리 예정)
//	@RequestMapping(value = "/reportDetailView.do")
//	public String reportDetailView(ReportVO rvo, Model model) {
//		model.addAttribute("userId", rvo.getUserId()); // 멤버 ID
//		model.addAttribute("rpList", reportService.selectOne(rvo));
//		return "report_manage_detail.jsp";
//	}

	// (관리자) Report 게시글 신고 처리
	@RequestMapping(value = "/updateReport.do")
	public String updateReport(ReportVO rpVo) {

		//넘겨받는 것
		//: targetNum, reportNum, userId(vo), reporterId(vo), reportStep, proStatus
		//warnCnt+1

		if(rpVo.getProcStatus().equals("cancle")) { //신고 취소, 신고한 사람에게 경고 +1
			
			
		} 
		else if(rpVo.getProcStatus().equals("ok")) { // 신고 처리, 신고당한 사람에게 경고 +1
			if(rpVo.getReportStat()==1) { // 글일 경우
				BoardVO bvo = new BoardVO();
				bvo.setBoardNum(rpVo.getTargetNum());
				bvo.setBoardStatus(3);
				boardService.update(bvo);
			} 
			else if(rpVo.getReportStat()==2 || rpVo.getReportStat()==3) { // 댓글일 경우
				ReplyVO rvo = new ReplyVO();
				rvo.setReplyStatus(3);
				rvo.setReplyNum(rpVo.getTargetNum());
				replyService.update(rvo);
			} 
			//신고 완료
			rpVo.setReportStat(2);
			reportService.update(rpVo);
		} 
		return "report_manage.jsp";
	}
	
}


