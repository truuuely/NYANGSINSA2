package com.nss.controller;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartRequest;

import com.nss.product.ProductService;
import com.nss.product.ProductVO;
import com.nss.review.ReviewService;
import com.nss.review.ReviewVO;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productServiceImpl;
	@Autowired
	private ReviewService reviewServiceImpl;
	

	//미완 사유: 저 리스트 조건은 어떻게 해결해야 함?
	// 멤버, 상품 
	@RequestMapping(value="/main.do")
	public String mainView(ProductVO pvo, Model model) {
		// 신상품 데이터. pvo : category == all, sort == regiDesc
		pvo.setCategory("all");
		pvo.setSort("regiDesc");
		pvo.setSearchLowPrice(0);
		pvo.setSearchHighPrice(1000000);
		model.addAttribute("newPList", productServiceImpl.selectAll(pvo));
		
		// 인기 상품. pvo : category == all, sort == sellDesc
		pvo.setSort("sellDesc");
		model.addAttribute("popPList", productServiceImpl.selectAll(pvo));
		
		return "main.jsp";
	}
	
	//미완 사유: 저 리스트 조건은 어케 해결해야 함? 순서도 맞는지 확실하지 않음
	// 파라미터별로 상이한 상품 목록들 setAttribute 하기
	// 참고 : shopping.do?category=all&sort=sellDesc
	@RequestMapping(value="shop/.do")
	public String shopView(ProductVO pvo,Model model) {
		model.addAttribute("data",pvo.getCategory());
		String category=pvo.getCategory();
		
		pvo.setCategory(category); // 카테고리 : all, food, treat, sand
		
		// 카테고리 별로 다른 페이지 
		
		// view에서 받아온 sort :
		// 		sellDesc (인기순:주문량순)
		// 		priceAsc (낮은 가격순)
		// 		priceDesc (높은 가격순)
		// 		regiDesc (최신순)
		pvo.setSort("regiDesc");
		pvo.setSearchLowPrice(0);
		pvo.setSearchHighPrice(1000000);
		
		model.addAttribute("pList", productServiceImpl.selectAll(pvo)); // pdao 에서 불러오기
		
		if(category.equals("all")) {
			return "shop.jsp";
		}
		else {
			return "shop_"+category+".jsp";
			//forward.setPath("shop_"+category+".jsp");
		}
	}
	
	//미완 사유: 저 리스트 조건은 어케 해결해야 함? + 셋은 언제 씀?
	@RequestMapping(value="shopDetails/.do")
	public String shopDetailView(ProductVO pvo,ProductSet ps,ReviewVO rvo,Model model) {
	      System.out.println("pNum: "+pvo.getpNum());
	      pvo = productServiceImpl.selectOne(pvo); // 해당 상품 및 달려있는 리뷰 set
	      
	      // 카테고리 별 인기상품 목록 가져오기 조건 : pName==null, 카테고리 nn, 정렬 nn
	      pvo.setCategory(pvo.getCategory()); // 관련상품 가져오기 위해 카테고리 set
	      pvo.setSort("sellDesc"); // 관련상품 가져오기 위해 정렬 set
	      pvo.setSearchLowPrice(0); 
	      pvo.setSearchHighPrice(1000000);
	      
	      ArrayList<ReviewVO> rList = reviewServiceImpl.selectAll(rvo); // 리뷰 리스트
	      ArrayList<ProductVO> pList = productServiceImpl.selectAll(pvo); // 관련 상품 리스트
	      
	      model.addAttribute("pvo", pvo);
	      model.addAttribute("rList", rList);
	      model.addAttribute("pList", pList);
	      
	      return "shop_details.jsp";
	}

	//미완 사유: 이거 미완인 거 같아서 놔둠
	@RequestMapping(value="createProduct/.do")
	public String insertProduct() {
		
		//미완인 거 같아 일단 남겨두겠습니다
		// 1. 파라미터 받아오기 : 카테고리, 상품 이름, 가격, 재고, 설명
		// 2. 상품 이미지 올리기 : 대표이미지(pImgUrl), 상세 이미지(pImgUrl2)
				
		return "product_manage_insert.jsp";
	}
	
	@RequestMapping(value="/updateProductPage.do")
	public String updateProuctView(ProductVO pvo, Model model) {
		productServiceImpl.selectOne(pvo); // pNum을 받아 해당 번호를 갖고 있는 상품 가져오기
		return "product_manage_detail.jsp";
	}
	
	//미완 사유: uploadDir, MultipartRequest 등을 스프링에서 어떻게 바꿔야하는지 모르겠어서 일단 남겨놓음
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
		      
		      if (!productServiceImpl.update(pvo)) { // 실패 시 알림창
		         PrintWriter out = response.getWriter();
		         response.setContentType("text/html; charset=utf-8");
		         out.println("<SCRIPT>alert('ERROR : UPDATE 실패');</SCRIPT>");
		         //forward.setPath(null);
		      }
		      
		      return "redirect:product_manage.jsp";
		   
	}
	

	//미완 사유: null에 무엇을 쓰고 싶었던걸까..
	@RequestMapping(value="/search.do")
	public String selectAllProductSearch(ProductVO pvo,Model model) {
		model.addAttribute("data",pvo.getpSearchContent()); //뷰에서 받은 searchContent를 그대로 뷰로 보냄
		
//		if(pvo.getpSearchContent() == null) {
//		
//	}
		
		return "search_result.jsp";
	}
	
}
