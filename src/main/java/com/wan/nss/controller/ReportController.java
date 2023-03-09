package com.wan.nss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wan.nss.biz.report.ReportService;
import com.wan.nss.biz.report.ReportVO;

@Controller
public class ReportController {

	@Autowired
	private ReportService reportService;
	
	// (관리자) 신고글 관리 페이지 이동
	@RequestMapping(value = "/ReportView.do")
	public String reportManageView(ReportVO rvo, Model model) {// rvo? vpVo? 일단 전자로 씁니다
		model.addAttribute("userId", rvo.getUserId()); // 멤버 ID
		model.addAttribute("rpList", reportService.selectAll(rvo)); // 나머지 정보등 꺼내쓸 수 있는 곳: 여기서 맞게 꺼내쓰세요
		return "report_manage.jsp";
	}

	// (관리자) 신고 게시글 상세보기 페이지 이동 (확실하면 주석처리 예정)
	@RequestMapping(value = "/reportDetailView.do")
	public String reportDetailView(ReportVO rvo, Model model) {
		model.addAttribute("userId", rvo.getUserId()); // 멤버 ID
		model.addAttribute("rpList", reportService.selectOne(rvo));
		return "report_manage_detail.jsp";
	}

	// (관리자) Report 게시글 신고 취소
	@RequestMapping(value = "/updateReport.do")
	public String updateReport(ReportVO rvo) {
		reportService.update(rvo);
		return "report_manage.jsp";
	}

}
