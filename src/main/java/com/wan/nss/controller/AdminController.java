package com.wan.nss.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wan.nss.biz.member.MemberDAO;
import com.wan.nss.biz.member.MemberService;
import com.wan.nss.biz.member.MemberVO;
import com.wan.nss.biz.order.OrderService;
import com.wan.nss.biz.order.OrderVO;
import com.wan.nss.biz.orderdetail.OrderDetailService;
import com.wan.nss.biz.orderdetail.OrderDetailVO;
import com.wan.nss.biz.product.ProductService;
import com.wan.nss.biz.review.ReviewService;

@Controller
public class AdminController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private ProductService productService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderDetailService orderDetailService;
	@Autowired
	private ReviewService reviewService;
	
	
		
	@RequestMapping(value = "/crawling.do") // 관리자 페이지 회원 관리 페이지 열기
	public String crawling() {
			return "crawling.jsp";
	}
	
	@RequestMapping(value = "/adminIndex.do") // 관리자 홈 페이지 열기
	public String adminIndexView(MemberVO mvo, MemberDAO memberDAO, OrderVO ovo, OrderDetailVO odvo, Model model, HttpSession session, HttpServletResponse response) {
		
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
		}
		else {
			
			List<OrderVO> oList;
			ovo.setoSearchCondition("all");
			oList = orderService.selectAll(ovo); // 전체 주문 상품 불러오기 (관리자용)

			for (int i = 0; i < oList.size(); i++) {
				ovo.setoNum(oList.get(i).getoNum());
				// totalPrice : 주문 당 총 금액
				int totalPrice = orderService.selectOne(ovo).getoPrice();
				oList.get(i).setoPrice(totalPrice); // 불러온 주문에 총 결제금액 set
			}
			System.out.println(oList);
			model.addAttribute("oList", oList); // 주문 내역 데이터
			model.addAttribute("memberTotal", memberDAO.selectAll(mvo).size()); // 총 회원 데이터

			return "admin_index.jsp";
		}
		
	}
	
	@RequestMapping(value = "/memberManagePage.do") // 관리자 페이지 회원 관리 페이지 열기
	public String selectAllMemberManage(HttpSession session, HttpServletResponse response) {
		
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
		}
		else {
			return "member_manage.jsp";
		}
		
	}
	
	@RequestMapping(value = "/productManagePage.do") // 관리자 페이지 회원 관리 페이지 열기
	public String selectAllProductManage(HttpSession session, HttpServletResponse response) {
		
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
		}
		else {
			return "product_manage.jsp";
		}
		
	}
	
	@RequestMapping(value = "/orderManagePage.do") // 관리자 페이지 회원 관리 페이지 열기
	public String selectAllorderDetailManage(HttpSession session, HttpServletResponse response) {
		
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
		}
		else {
			return "order_manage.jsp";
		}
		
	}
	
	@RequestMapping(value = "/reviewManagePage.do") // 관리자 페이지 회원 관리 페이지 열기
	public String selectAllReviewManage(HttpSession session, HttpServletResponse response) {
		
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
		}
		else {
			return "review_manage.jsp";
		}
		
	}
	
}
