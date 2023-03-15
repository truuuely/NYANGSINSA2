package com.wan.nss.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
      //targetNum, reportStep, userID, reportContent
      // 폼 안에서 체크된(활성화 된) 값만 오므로 처리하면 됨
	   
	  //setting된 input 확인
	  System.out.println("targetNum: "+rpvo.getTargetNum());
	  System.out.println("reportStep: "+rpvo.getReportStep());
	  System.out.println("userId: "+rpvo.getUserId());
	  System.out.println("reportContent: "+rpvo.getReportContent());
	  
	  reportService.insert(rpvo);
	  
	  return "board_detail.jsp";
   }

   // (관리자) 신고글 관리 페이지 이동 (댓글, 대댓글 세팅은 알아서 하고 있음.. 이동만 시켜주긴)
   @RequestMapping(value = "/reportManageView.do")
   public String reportManageView(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {
	   String id = (String) session.getAttribute("memberId");
		if (id == null || !(id.equals("admin"))) { // 로그인을 안 하거나 admin이 아니면 접근 권한 없음.
			try {
				response.setContentType("text/html; charset=utf-8");
				response.getWriter().println("<SCRIPT>alert('접근 권한이 없습니다.');</SCRIPT>");
				
				return "main.do";
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return "report_manage.jsp";
		}
   }

//   // (관리자) 신고 게시글 상세보기 페이지 이동 (보드디테일과 동일해서 뻄)
//   @RequestMapping(value = "/reportDetailView.do")
//   public String reportDetailView(ReportVO rpvo, Model model) {
//	   
//   		model.addAttribute("rpList", reportService.selectOne(rpvo));
//   		return "report_manage_detail.jsp";
//   }

   // (관리자) Report 게시글 신고 처리
   @RequestMapping(value = "/updateReport.do")
   public String updateReport(ReportVO rpvo, MemberVO mvo) {

      //넘겨받는 것
      //: targetNum, reportNum, userId(vo), reporterId(vo), reportStep, proStatus
      //warnCnt+1

      if(rpvo.getProcStatus().equals("cancle")) { //신고 취소, 신고한 사람에게 경고 +1
    	  mvo.setUserId(rpvo.getReporterId());
    	  memberService.update(mvo);
      } 
      else if(rpvo.getProcStatus().equals("ok")) { // 신고 처리, 신고당한 사람에게 경고 +1
    	  mvo.setUserId(rpvo.getUserId());
    	  memberService.update(mvo);
    	  
    	  if(rpvo.getReportStat()==1) { // 글일 경우
            BoardVO bvo = new BoardVO();
            bvo.setBoardNum(rpvo.getTargetNum());
            bvo.setBoardStatus(3);
            boardService.update(bvo);
         } 
         else if(rpvo.getReportStat()==2 || rpvo.getReportStat()==3) { // 댓글일 경우
            ReplyVO rvo = new ReplyVO();
            rvo.setReplyStatus(3);
            rvo.setReplyNum(rpvo.getTargetNum());
            replyService.update(rvo);
         } 
         //신고 완료
         rpvo.setReportStat(2);
         reportService.update(rpvo);
      } 
      return "report_manage.jsp";
   }
   
}
