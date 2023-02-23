package com.wan.nss.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wan.nss.product.ProductService;
import com.wan.nss.product.ProductVO;
import com.wan.nss.review.ReviewDAO;
import com.wan.nss.review.ReviewService;
import com.wan.nss.review.ReviewVO;

@Controller
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	@Autowired
	private ProductService productService;

	//미완 사유: 문영님 isWritten 이게 뭐에요?
	@RequestMapping(value="/reviewPage.do")
	public String reviewView(ReviewVO rvo,ProductVO pvo,Model model,HttpSession session,
			HttpServletResponse response) {
		String rWriter = (String) session.getAttribute("memberId");
		System.out.println(rvo.getpNum());
		ArrayList<ReviewVO> rdatas = reviewService.selectAll(rvo);
		
		System.out.println("pNum: " + pvo.getpNum()); 	
		pvo = productService.selectOne(pvo); // 리뷰 작성 창 세팅하기

		model.addAttribute("pNum", pvo.getpNum());
		model.addAttribute("pImgUrl", pvo.getpImgUrl());
		model.addAttribute("pName", pvo.getpName());
		try {
		for (ReviewVO v : rdatas) {
			if (v.getrWriter().equals(rWriter)) { // 해당 상품의 리뷰목록의 작성자 중에 현재 로그인한 회원이 있을 경우
				//return "isWritten"; //forward.setPath("isWritten");
				response.getWriter().println("<SCRIPT>alert('이미 리뷰를 작성하셨습니다.'); window.close();</SCRIPT>"); // 리뷰 작성 창 닫기
				return "order_detail.jsp";
			}
		}
		}catch(Exception e) {
			System.out.println("추가됨");
			e.printStackTrace();
		}
		
		return "review.jsp";
	}
	//결합도가 높아서? 이 방법 말고 다른 방법을 찾으려고 한다?
	/*
	프론트 컨트롤러에 있던 창인데 결합도를 낮추려면 어떤 방향으로 가는 게 좋은가요?
	if (forward.getPath().equals("isWritten")) {
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().println("<SCRIPT>alert('이미 리뷰를 작성하셨습니다.'); window.close();</SCRIPT>"); // 리뷰 작성 창 닫기
			return;
		}
	*/

	@RequestMapping(value="/insertReview.do")
	public String insertReview(ReviewVO rvo, Model model,HttpServletResponse response) {
		response.setContentType("text/html; charset=utf-8");

		try {
			if(reviewService.insert(rvo)) { // 업데이트 성공  
				response.getWriter().println("<SCRIPT>alert('리뷰가 등록되었습니다.'); window.close();</SCRIPT>");
			}
			else { // 실패 시 
				response.getWriter().println("<SCRIPT>alert('에러 발생. 잠시 후 다시 시도해주세요.'); window.close();</SCRIPT>");
			}
		}catch(Exception e) { //실패 시
			System.out.println("insertReview오류: 알림창 에러 발생");
		}

		return "order_detail.jsp";
	}

	//당장은 안 쓰지만 관리자가 지우려고 남겨둠
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
				return "main.do";
			}
		}catch(Exception e) {
			System.out.println("deleteReview 오류: 접근 권한 없음 알림창 오류");
			e.printStackTrace();
		}

		try {
			if (!reviewService.delete(rvo)) { // 리뷰 삭제 실패 시
				response.setContentType("text/html; charset=utf-8");
				response.getWriter().println("<SCRIPT>alert('Delete 실패. 잠시 후 다시 시도하세요.');</SCRIPT>");
				//return ; //빈칸에는 추후 리뷰 상세페이지로 이동할 예정
			}
		}catch(Exception e) {
			System.out.println("deleteReview 오류: delete 실패 알림창 오류");
			e.printStackTrace();
		}

		return "redirect:review_manage.jsp"; //null, forward 상황
	}


}
