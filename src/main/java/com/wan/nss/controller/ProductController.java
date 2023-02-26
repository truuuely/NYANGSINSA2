package com.wan.nss.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wan.nss.biz.product.ProductService;
import com.wan.nss.biz.product.ProductVO;
import com.wan.nss.biz.review.ReviewService;
import com.wan.nss.biz.review.ReviewVO;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;
	@Autowired
	private ReviewService reviewService;


	// 멤버, 상품 
	@RequestMapping(value="/main.do")
	public String mainView(ProductVO pvo, Model model) {
		// 신상품 데이터. pvo : category == all, sort == regiDesc
		pvo.setpCategory("all");
		pvo.setSort("regiDesc");
		pvo.setSearchLowPrice(0);
		pvo.setSearchHighPrice(1000000);
		model.addAttribute("newPList", productService.selectAll(pvo)); 

		// 인기 상품. pvo : category == all, sort == sellDesc //돌려봐야 위의 내용이 아래에 그대로 적용되는지 알 거 같음
		pvo.setSort("sellDesc");
		model.addAttribute("popPList", productService.selectAll(pvo));

		return "main.jsp";
	}

	// 파라미터별로 상이한 상품 목록들 setAttribute 하기
	// 참고 : shopping.do?category=all&sort=sellDesc
	@RequestMapping(value="shop/.do")
	public String shopView(ProductVO pvo,Model model) {

		//사용자에게 받은 카테고리는 자동매핑하여 세팅됨
		
		// view에서 받아온 sort :
		// 		sellDesc (인기순:주문량순)
		// 		priceAsc (낮은 가격순)
		// 		priceDesc (높은 가격순)
		// 		regiDesc (최신순)
		pvo.setSort("regiDesc");
		pvo.setSearchLowPrice(0);
		pvo.setSearchHighPrice(1000000);

		model.addAttribute("pList", productService.selectAll(pvo)); // pdao 에서 불러오기

		// 카테고리 : all, food, treat, sand
		// 카테고리 별로 다른 페이지 
		if(pvo.getpCategory().equals("all")) {
			return "shop.jsp";
		}
		return "shop_"+pvo.getpCategory()+".jsp";
	}

	@RequestMapping(value="shopDetails/.do")
	public String shopDetailView(ProductVO pvo,ReviewVO rvo,Model model) {
		System.out.println("pNum: "+pvo.getpNum());
		pvo = productService.selectOne(pvo); // 해당 상품 및 달려있는 리뷰 set

		// 카테고리 별 인기상품 목록 가져오기 조건 : pName==null, 카테고리 nn, 정렬 nn
		pvo.setpCategory(pvo.getpCategory()); // 관련상품 가져오기 위해 카테고리 set
		pvo.setSort("sellDesc"); // 관련상품 가져오기 위해 정렬 set
		pvo.setSearchLowPrice(0); 
		pvo.setSearchHighPrice(1000000);

		ArrayList<ReviewVO> rList = reviewService.selectAll(rvo); // 리뷰 리스트
		ArrayList<ProductVO> pList = productService.selectAll(pvo); // 관련 상품 리스트

		model.addAttribute("pvo", pvo);
		model.addAttribute("rList", rList);
		model.addAttribute("pList", pList);

		return "shop_details.jsp";
	}


	/*
	@RequestMapping(value="/createProduct.do")
	public String insertProduct() {

		//없는 기능이므로 주석처리합니다.
		// 1. 파라미터 받아오기 : 카테고리, 상품 이름, 가격, 재고, 설명
		// 2. 상품 이미지 올리기 : 대표이미지(pImgUrl), 상세 이미지(pImgUrl2)

		return "product_manage_insert.jsp";
	}
	 */

	@RequestMapping(value="/updateProductPage.do")
	public String updateProuctView(ProductVO pvo, Model model) {
		productService.selectOne(pvo); // pNum을 받아 해당 번호를 갖고 있는 상품 가져오기
		return "product_manage_detail.jsp";
	}

	/*미완 사유: CKEditor에서 어떻게 값을 전달하는지 알지 못해서 주석처리만 함. 추후 알게 되면 주석 풀고 수정 예정
	@RequestMapping(value="/updateProduct.do")
	public String updateProduct(ProductVO pvo, Model model,
			HttpServletResponse response,HttpServletRequest request) {

		// 관리자 모드 : 해당 상품 관리 페이지에서 "수정" 버튼 클릭 시 실제 수정
		System.out.println("updateProduct 입장");

		// 각자 이미지 저장할 위치
		String uploadDir = this.getClass().getResource("").getPath();

		System.out.println(uploadDir);

		// 총 100M 까지 저장 가능하게 함
		int maxSize = 1024 * 1024 * 100;

		String encoding = "UTF-8";

		// 사용자가 전송한 파일정보 토대로 업로드 장소에 파일 업로드 수행할 수 있게 함

		MultipartRequest multipartRequest = new MultipartRequest(request, uploadDir, maxSize, encoding, new DefaultFileRenamePolicy());

		// 중복된 파일이름이 있기에 fileRealName이 실제로 서버에 저장된 경로이자 파일

		//String fileRealName = multipartRequest.getFilesystemName("file");

		// 디비에 업로드 메소드

		//new FileDAO().upload(fileName, fileName2);

		//pvo.setpImgUrl("img/"+multipartRequest.getFilesystemName("img")); // 이미지 첨부파일인데.. 어떻게 하지 수정 필요!!!!

		pdao.update(pvo);

		if (!productService.update(pvo)) { // 실패 시 알림창
			PrintWriter out = response.getWriter();
			response.setContentType("text/html; charset=utf-8");
			out.println("<SCRIPT>alert('ERROR : UPDATE 실패');</SCRIPT>");
			//forward.setPath(null);
		}

		return "redirect:product_manage.jsp";

	}*/

	//추후 보드까지 검색되게 수정 예정
	@RequestMapping(value="/search.do")
	public String selectAllProductSearch(ProductVO pvo,Model model) {
		model.addAttribute("data",pvo.getpSearchContent()); //뷰에서 받은 searchContent를 그대로 뷰로 보냄

		return "search_result.jsp";
	}

}
