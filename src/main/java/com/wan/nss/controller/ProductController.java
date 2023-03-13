package com.wan.nss.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.wan.nss.biz.board.BoardService;
import com.wan.nss.biz.common.Crawling;
import com.wan.nss.biz.image.ImageService;
import com.wan.nss.biz.image.ImageVO;
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
	@Autowired
	private ImageService imageService;
	@Autowired
	private Crawling crawling;

	// 멤버, 상품
	@RequestMapping(value = "/main.do")
	public String mainView(ProductVO pvo, ProductVO pvo2, Model model, HttpServletRequest request) {
		// 신상품 데이터. pvo : category == all, sort == regiDesc
		System.out.println("   로그: main.do");

		// 크롤링
		pvo.setCategory("all");
		pvo.setSort("regiDesc");
		pvo.setSearchLowPrice(0);
		pvo2.setpSearchCondition("max"); // selectOne에서 인자로 쓸 것
		pvo.setSearchHighPrice(productService.selectOne(pvo2).getPrice());

		List<ProductVO> datas = productService.selectAll(pvo);
		System.out.println();
		System.out.println("-----------------------------------------------------");
		System.out.println("상품 개수: " + datas.size()); // 상품 개수 로그
		if (datas.size() < 48) {
			for (ProductVO v : datas) {
				System.out.println("상품 번호: " + v.getpNum());
			}			
			System.out.println("-----------------------------------------------------");
			crawling.sample(request);
		}
		else {
			System.out.println("이미 크롤링 됨");
		}

		// 전체 최신순(등록일순): category = all, sort = regiDesc
		model.addAttribute("newPList", productService.selectAll(pvo));

		// 전체 인기순(판매량순): category == all, sort == sellDesc
		pvo.setSort("sellDesc");
		model.addAttribute("popPList", productService.selectAll(pvo)); //주문데이터가 있어야 가능 (지금 사료밖에 없음)
		model.addAttribute("lang",request.getParameter("lang"));
		
		return "main.jsp";
	}

	// 쇼핑페이지 이동
	@RequestMapping(value = "/shop.do")
	public String shopView(ProductVO pvo, ProductVO pvo2, Model model, HttpSession session) {
		// 파라미터별로 상이한 상품 목록들 세팅하기: shopping.do?category=???
		// 디폴트값: 인기순, 찾을 가격 0 ~ 1000000

		// 할인상품 정렬
		pvo.setpSearchCondition("dc");
		pvo.setSort("sellDesc"); // sort 종류: sellDesc (인기순:주문량순) / priceAsc (낮은 가격순) / priceDesc (높은 가격순) /
		// regiDesc (최신순)
		pvo.setSearchLowPrice(0);
		pvo2.setpSearchCondition("max"); // selectOne에서 인자로 쓸 것
		pvo.setSearchHighPrice(productService.selectOne(pvo2).getPrice());
		model.addAttribute("maxPrice",pvo.getSearchHighPrice());//
		model.addAttribute("pList", productService.selectAll(pvo));

		// 쇼핑페이지 기본 이동 (shop.do?category=all)
		if (pvo.getCategory().equals("all")) {
			return "shop.jsp";
		}
		// 카테고리별 쇼핑페이지 기본 이동 (shop.do?category=food/treat/sand)
		else if (pvo.getCategory().equals("food") || pvo.getCategory().equals("treat")
				|| pvo.getCategory().equals("sand")) {
			return "shop_" + pvo.getCategory() + ".jsp"; // 카테고리 별로 다른 페이지 이동 (all, food, treat, sand)
		}
		return null;// nullPointException 내서 페이지 이동하려고
	}

	// 상품세부페이지 이동: 추후 pvo2 지울 예정
	@RequestMapping(value = "/shopDetails.do")
	public String shopDetailView(ProductVO pvo, ProductVO pvo2, ImageVO ivo, ReviewVO rvo, Model model) {
		System.out.println("pNum: " + pvo.getpNum());
		
		ProductVO resPvo = productService.selectOne(pvo); // 해당 상품 및 달려있는 리뷰 set,  상세페이지에서 보여줄 상품num을 selectAll에 돌린 결과를 resPvo에 저장
			
		// 상세이미지 불러오기
	    ivo.setTargetNum(resPvo.getpNum()); // 상품pk를 이미지pk에 세팅
	    ivo.setTypeNum(102);
	    ImageVO selectIvo = imageService.selectOne(ivo); // 세팅된 이미지pk를 가지고 selectOne하여 상세이미지 정보 불러오기
	    resPvo.setImageName2(selectIvo.getImageName()); // 상세이미지가 selectOne된 ivo의 imageName을 pvo의 ImageName2에 세팅		
		System.out.println("resPvo.getImageName2: "+resPvo.getImageName2()); // 확인

		//해당 제품의 리뷰 보기
		rvo.setrSearchCondition("pNum"); 
		rvo.setpNum(resPvo.getpNum()); //리뷰 num을 해당 상품의 num으로 세팅
		
		// 관련상품 목록 가져오기 조건 : pName==null, 카테고리 nn, 정렬 nn
		pvo.setCategory(resPvo.getCategory());
		pvo.setSort("sellDesc"); // 관련상품(선택된 카테고리의 인기상품들) 가져오기 위해 정렬 set
		pvo.setSearchLowPrice(0);
		pvo2.setpSearchCondition("max"); // selectOne에서 인자로 쓸 것
		pvo.setSearchHighPrice(productService.selectOne(pvo2).getPrice());

		//이거 결과값
		ArrayList<ReviewVO> rList = reviewService.selectAll(rvo); // 리뷰 리스트 
		ArrayList<ProductVO> pList = productService.selectAll(pvo); // 관련 상품 리스트

		System.out.println(rList);
		System.out.println(pList);
		
		model.addAttribute("pvo", resPvo); // 해당 상품의 정보들을 보내줌
		model.addAttribute("rList", rList); //해당 리뷰를 받고 있음??? 뷰에서 확인을 해봐야 할 거 같음.. 이 이상으로 뭘 해줄 수가 없음
		model.addAttribute("pList", pList);

		return "shop_details.jsp";
	}

	// (관리자)상품 추가 기능: 해당 상품 관리 페이지에서 
	
	// (관리자)상품 수정 기능: 해당 상품 관리 페이지에서 "수정" 버튼 클릭 시 실제 수정
	@RequestMapping(value = "/updateProduct.do", method = RequestMethod.POST)
	public String updateProduct(ProductVO pvo, ImageVO ivo, Model model, HttpServletResponse response,
			HttpServletRequest request) {
		// @RequestParam(value="searchCondition",defaultValue="TITLE",required=false)String
		// searchCondition

		// 관리자 모드 : 해당 상품 관리 페이지에서 "수정" 버튼 클릭 시 실제 수정 (아직 안 함)
		System.out.println("updateProduct 입장");
		
		// 1. 파일 받아오기: downloadFile()의 역할: url을 인식하고, 해당 파일을 복사 및 저장시키는 것까지 진행함
		//업로드한 파일을 본섭에 이동시키는 것 까지 진행시킴
		
		
		// 2. 현재 이미지 파일이 비어있다면 디폴트파일로 세팅하고 업로드 진행, 
		// 아니라면 바로 업로드를 실행
		
		// 이미지 파일이 비어있지 않은 경우,
		// 업로드한 파일이름 추출, 추출한 파일이름을 파일이름 vo에 set하는 과정을 빼는 이유는, 
		// 파일이름에 targetNum와 typeNum이라는 주요정보가 포함되어있음 
		// >> 바꾸지 않고 사진만 바꿀 예정
		
		// 업로드 진행
		boolean flag = productService.update(pvo);
		
		///////////////////////////////////////////////
		// 파일을 받기 위해 다운로드 파일 메서드 불러오기
		// crawling.downloadFile(new URL("원래 이미지 경로"), 실제로 저장할 이미지 경로); //input: Url
		// url, String fileName

		// 파일받기 (저장공간) : V에서 img, img2로 줌

		
		// 2. 세팅 (업로드 확인 > bvo.setFile(fileName) + 만약 파일의 크기가 기존 것과 동일하다면, 기존 것보다 크거나
		// 작다면, 그렇게 조정하는 코드도 같이 넣기)



		

		if (!flag) { // 실패 시 알림창
			try {
				PrintWriter out = response.getWriter();
				response.setContentType("text/html; charset=utf-8");
				out.println("<SCRIPT>alert('ERROR : UPDATE 실패');</SCRIPT>");
				return "product_manage_datail.jsp";
			} catch (Exception e) {
				System.out.println("실패 알람칭 띄우기 중 오류 발생");
				e.printStackTrace();
			}
		}
		return "redirect:product_manage.jsp";
	}

	//일단 원인은 찾았고, 서치가 되는지 확인 필요
	@RequestMapping(value = "/search.do", method = RequestMethod.POST)
	public String selectAllProductSearch(ProductVO pvo, Model model) {
		System.out.println("pSearchCondition: " + pvo.getpSearchCondition());
		System.out.println("pSearchContent: " + pvo.getpSearchContent());

		model.addAttribute("pSearchContent",pvo.getpSearchContent()); 
		model.addAttribute("pList", productService.selectAll(pvo));

		return "search_result.jsp";
	}

	//ListController
	@ResponseBody
	@RequestMapping(value="/getList.do", method = RequestMethod.POST)
	public JsonArray sendList(ProductVO pvo, Model model) {

		System.out.println("getList.do 진입");

		System.out.println("ListController 실행 조건: ");
		System.out.println("pSearchCondition: " + pvo.getpSearchCondition());
		System.out.println("category: " + pvo.getCategory());
		System.out.println("sort: " + pvo.getSort());
		System.out.println("searchLowPrice: " + pvo.getSearchLowPrice());
		System.out.println("searchHighPrice: " + pvo.getSearchHighPrice());

		// 뷰에서 넘어온 조건으로 상품리스트 가져오기
		ArrayList<ProductVO> list = productService.selectAll(pvo); // 결과 상품 목록

		// 뷰에서 전체상품 최고가 가져온 후 response에 세팅
		pvo.setpSearchCondition("max");
		model.addAttribute("maxPrice", productService.selectOne(pvo).getPrice());

		JsonArray datas = new Gson().toJsonTree(list).getAsJsonArray(); // JsonArry로 변경하여 반환

		return datas;
	}

	//SearchListController 이게 작동을 안 한다는 이야기임
	@ResponseBody
	@RequestMapping(value="getSearchList.do", method = RequestMethod.POST)
	public JsonArray getSearchList(ProductVO pvo, ProductVO pvo2, JSONArray jsonArray, Model model) {
		// ajax가 보낸 searchContent
		System.out.println("pSearchContent: "+pvo.getpSearchContent());
		
		// ajax()로 넘어온 category, sort를 세팅한 pvo를 가지고 selectAll 
		pvo.setSearchLowPrice(0);
		pvo2.setpSearchCondition("max"); // selectOne에서 인자로 쓸 것
		pvo.setSearchHighPrice(productService.selectOne(pvo2).getPrice());
		ArrayList<ProductVO> list = productService.selectAll(pvo); // 상품 검색 결과
		
		JsonArray datas = new Gson().toJsonTree(list).getAsJsonArray(); 
		
		return datas;
	}

}
