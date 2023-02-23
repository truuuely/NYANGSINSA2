package com.nss.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nss.product.ProductDAO;
import com.nss.product.ProductService;
import com.nss.product.ProductVO;
import com.nss.review.ReviewDAO;
import com.nss.review.ReviewService;
import com.nss.review.ReviewVO;

@Controller
public class ReviewController {
	
	@Autowired
	private ReviewService reviewServiceImpl;
	@Autowired
	private ProductService productServiceImpl;

	//미완 사유: isWritten 이게 뭐에요?
	@RequestMapping(value="reviewPage/.do")
	public String reviewView(ReviewVO rvo,ProductVO pvo,Model model,HttpSession session) {
		String rWriter = (String) session.getAttribute("memberId");
		ArrayList<ReviewVO> rdatas = reviewServiceImpl.selectAll(rvo);
		System.out.println("pNum: " + pNum);
		pvo = productServiceImpl.selectOne(pvo); // 리뷰 작성 창 세팅하기
		
		model.addAttribute("pNum", pvo.getpNum());
		model.addAttribute("pImgUrl", pvo.getpImgUrl());
		model.addAttribute("pName", pvo.getpName());
		for (ReviewVO v : rdatas) {
			if (v.getrWriter().equals(rWriter)) { // 해당 상품의 리뷰목록의 작성자 중에 현재 로그인한 회원이 있을 경우
				return "isWritten"; //forward.setPath("isWritten");
			}

		}
		return "review.jsp";
	}

	//미완 사유: 보낼 링크를 모름
	@RequestMapping(value="/insertReview.do")
	public String insertReview(ReviewVO rvo, ReviewDAO rdao, Model model,HttpServletResponse response) {
		rdao.insert(rvo);
		
		response.setContentType("text/html; charset=utf-8");
		
		try {
		if(rdao.insert(rvo)) { // 업데이트 성공  
			response.getWriter().println("<SCRIPT>alert('리뷰가 등록되었습니다.'); window.close();</SCRIPT>");
		}
		else { // 실패 시 
			response.getWriter().println("<SCRIPT>alert('에러 발생. 잠시 후 다시 시도해주세요.'); window.close();</SCRIPT>");
		}
		}catch(Exception e) { //실패 시
			System.out.println("insertReview오류: 알림창 에러 발생");
		}
		
		return "";//링크 직접 찾아야 됨 ㅅㄱ
	}
	
	@RequestMapping(value="/.do")
	public String updateBoard(VO vo, DAO DAO, Model model) {
		
	}
	
	//안 돌려봤음
	//미완 사유: 실패한 경우 경로 넣는거 안함
	@RequestMapping(value="/deleteReview.do")
	public String deleteReview(ReviewVO rvo, ReviewDAO rdao, HttpSession session,
			HttpServletResponse response, HttpServletRequest request) {
		System.out.println("deleteReview 입장");
		
		String id = (String)session.getAttribute("memberId");
		
		try {
		if (id == null || !(id.equals("admin"))) {
			// 로그인을 안 하거나 admin이 아니면 접근 권한 없음.
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().println("<SCRIPT>alert('접근 권한이 없습니다.');</SCRIPT>");
		}
		}catch(Exception e) {
			System.out.println("deleteReview 오류: 접근 권한 없음 알림창 오류");
			e.printStackTrace();
		}
		
		try {
		// 1. 파라미터로 rNum 받아오기
				int rNum = Integer.parseInt(request.getParameter("rNum"));
				// 2. 삭제
				boolean flag = rdao.delete(rvo);
				if (!flag) { // 리뷰 삭제 실패 시
					response.setContentType("text/html; charset=utf-8");
					response.getWriter().println("<SCRIPT>alert('Delete 실패. 잠시 후 다시 시도하세요.');</SCRIPT>");
				}
		}catch(Exception e) {
			System.out.println("deleteReview 오류: delete 실패 알림창 오류");
			e.printStackTrace();
		}
		
		return "redirect:review_manage.jsp"; //null, forward 상황
	}

	
}
