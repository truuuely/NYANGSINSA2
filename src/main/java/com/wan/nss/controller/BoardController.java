package com.wan.nss.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wan.nss.biz.blike.BlikeService;
import com.wan.nss.biz.blike.BlikeVO;
import com.wan.nss.biz.board.BoardService;
import com.wan.nss.biz.board.BoardVO;
import com.wan.nss.biz.image.ImageService;
import com.wan.nss.biz.image.ImageVO;
import com.wan.nss.biz.member.MemberService;
import com.wan.nss.biz.member.MemberVO;
import com.wan.nss.biz.reply.ReplyService;
import com.wan.nss.biz.reply.ReplyVO;

@Controller
public class BoardController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private BoardService boardService;
	@Autowired
	private BlikeService blikeService;
	@Autowired
	private ImageService imageService;
	@Autowired
	private ReplyService replyService;

	// 고양이 자랑 게시판 페이지 진입
	@RequestMapping(value = "/boardView.do")
	public String boardView(BoardVO bvo, Model model, HttpSession session) {

		System.out.println("boardView.do 진입");
		bvo.setUserId((String) session.getAttribute("memberId"));

		// 전체 자랑글 목록 : bList
		model.addAttribute("bList", boardService.selectAll(bvo));

		// TOP3 자랑글 목록 : top3List
		bvo.setSearchCondition("top3");
		model.addAttribute("top3List", boardService.selectAll(bvo));

		System.out.println();

		return "board.jsp";
	}

	// 고양이 자랑 게시판 게시글 상세보기 페이지 진입(글작성 직후)
	@RequestMapping(value = "/boardPostViewFirst.do")
	public String boardPostViewFirst(MemberVO mvo, BoardVO bvo, Model model, HttpSession session) {
		
		System.out.println("boardPostViewFirst.do 진입");
		System.out.println("bvo.boardNum: " + bvo.getBoardNum());
		ReplyVO rvo = new ReplyVO();
		rvo.setBoardNum(bvo.getBoardNum());
		
		// 게시글 상세페이지에서 수정 버튼 활성화를 위한 memberId
		mvo.setUserId((String) session.getAttribute("memberId"));
		bvo.setUserId((String) session.getAttribute("memberId"));
		MemberVO loginMvo = memberService.selectOne(mvo);
		     
		System.out.println("replyset 길이 :"+replyService.selectAll(rvo).size());
		// 게시글 상세 데이터
		model.addAttribute("replyset",replyService.selectAll(rvo));
		model.addAttribute("board", boardService.selectOne(bvo));
		model.addAttribute("member", loginMvo);

		return "board_detail.jsp";

	}
	
	// 고양이 자랑 게시판 게시글 상세보기 페이지 진입
	@RequestMapping(value = "/boardPostView.do")
	public String boardPostView(MemberVO mvo, BoardVO bvo, Model model, HttpSession session) {
		
		System.out.println("boardPostView.do 진입");
		System.out.println("bvo.boardNum: " + bvo.getBoardNum());
		ReplyVO rvo = new ReplyVO();
		rvo.setBoardNum(bvo.getBoardNum());
		
		// 조회수 증가 로직
		boardService.update(bvo);
		
		// 게시글 상세페이지에서 수정 버튼 활성화를 위한 memberId
		mvo.setUserId((String) session.getAttribute("memberId"));
		bvo.setUserId((String) session.getAttribute("memberId"));
		MemberVO loginMvo = memberService.selectOne(mvo);

		System.out.println("replyset 길이 :"+replyService.selectAll(rvo).size());
		// 게시글 상세 데이터
		model.addAttribute("replyset",replyService.selectAll(rvo));
		model.addAttribute("board", boardService.selectOne(bvo));
		model.addAttribute("member", loginMvo);

		return "board_detail.jsp";

	}

	// 고양이 자랑 게시판 검색 수행 및 검색결과 페이지 진입
	@RequestMapping(value = "/selectAllSearchBoard.do")
	public String selectAllSearchBoard(BoardVO bvo, Model model) {

		System.out.println("selectAllSearchBoard.do 진입");

		model.addAttribute("bList", boardService.selectAll(bvo));

		return "board_result.jsp";

	}

	// 고양이 자랑 게시판 새 게시글 작성하기 페이지 진입
	@RequestMapping(value = "/insertBoardView.do")
	public String insertBoardView(MemberVO mvo, HttpSession session, HttpServletResponse response) {
		// TODO
		// 세션 아이디 가져와서
		mvo.setUserId((String) session.getAttribute("memberId"));
		// 역할 가져와서
		MemberVO loginMvo = memberService.selectOne(mvo);

		// 차단된 회원은 보드홈으로 이동(얼럿 띄우기 : 글쓰기 기능이 차단된 회원입니다. 관리자에게 문의하세요.)
		if (loginMvo.getRole().equals("BLOCKED")) {
			try {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('글쓰기 기능이 차단된 회원입니다. 관리자에게 문의하세요');</script>");
				out.flush();
				return "boardView.do";
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			System.out.println("insertBoardView.do 진입");

			return "insert_board.jsp";
		}

	}

	// 고양이 자랑 게시판 새 게시글 작성하기 페이지에서
	// 게시글 작성(insert) 수행 및 해당 글 상세 보기(작성 결과 보기) 페이지로 이동
	@RequestMapping(value = "/insertBoard.do")
	public String insertBoard(BoardVO bvo, Model model) {

		System.out.println("insertBoard.do 진입");
		// 이미지 객체 생성
		ImageVO ivo = new ImageVO();
		// 가장 최근 게시글 찾아내서 B_NO 가져오기
		bvo.setSearchCondition("newest");
		BoardVO preBvo = boardService.selectOne(bvo);
		int bNum = 100;
		if(preBvo != null) {
			bNum = preBvo.getBoardNum() + 1;
		}
		bvo.setBoardNum(bNum);
		// 게시글 추가
		boardService.insert(bvo);

		// "img/" 문자열이 있는 동안 반복해서 ivo 저장하기
		String tag = bvo.getBoardContent();
		int typeNum = 201;
		while (true) {
			
			System.out.println("tag: " + tag);
			
			// "img/" 문자열이 있는 동안 반복해서 ivo 저장하기
			if (tag.indexOf("img/") >= 0) {
				
				// 이미지 번호 세팅
				ivo.setTargetNum(bNum);
				
				// 이미지 구분 번호 세팅
				ivo.setTypeNum(typeNum);
				
				// 이미지 이름 세팅
				// 이미지 태그 값 잘라내기
				String imageUrl = (String) tag.subSequence(tag.indexOf("img/"), tag.indexOf("\" style="));
				System.out.println(imageUrl);
				
				ivo.setImageName(imageUrl);
				
				// 이미지 추가
				imageService.insert(ivo);
				
				// 찾은 부분까지 잘라내고 다시 찾기위해 저장
				tag = tag.substring(tag.indexOf("\" style=") + 9);
				
				// typeNum ++
				typeNum ++;
				
			// "img/" 문자열이 없으면 종료
			} else { // 
				break;
			}

		}
		System.out.println("bvo.boardNum: " + bvo.getBoardNum());
		return "boardPostViewFirst.do?boardNum=" + bvo.getBoardNum() + "&searchCondition=viewCnt";

	}

	// 고양이 자랑 게시판 게시글 수정하기 페이지 진입
	@RequestMapping(value = "/updateBoardView.do")
	public String updateBoardView(BoardVO bvo, Model model) {

		System.out.println("updateBoardView.do 진입");
		System.out.println("bvo: " + bvo);
		
		model.addAttribute("board", boardService.selectOne(bvo));
		// 몇번 글 수정인지 설정하여 보내기
		return "update_board.jsp";

	}

	// 고양이 자랑 게시판 게시글 수정하기 페이지에서
	// 게시글 수정(update) 및 해당 글 상세 보기(수정 결과 보기) 페이지로 이동
	@RequestMapping(value = "/updateBoard.do")
	public String updateBoard(BoardVO bvo, Model model) {

		System.out.println("updateBoard.do 진입");
		ImageVO ivo = new ImageVO();
		
		// 게시글 수정
		boardService.update(bvo);

		// IMAGE 테이블에서 기존 이미지 행 삭제
		ivo.setTargetNum(bvo.getBoardNum()); // 게시글 번호
		ivo.setTypeNum(200); // 200: 게시글
		
		imageService.delete(ivo);
		
		// "img/" 문자열이 있는 동안 반복해서 ivo 저장하기
		String tag = bvo.getBoardContent();
		int typeNum = 201;
		while (true) {
			
			System.out.println("tag: " + tag);
			
			// "img/" 문자열이 있는 동안 반복해서 ivo 저장하기
			if (tag.indexOf("img/") >= 0) {
				
//				// 이미지 번호 세팅
//				ivo.setTargetNum(bvo.getBoardNum());
				
				// 이미지 구분 번호 세팅
				ivo.setTypeNum(typeNum);
				
				// 이미지 이름 세팅
				// 이미지 태그 값 잘라내기
				String imageUrl = (String) tag.subSequence(tag.indexOf("img/"), tag.indexOf("\" style="));
				System.out.println(imageUrl);
				
				ivo.setImageName(imageUrl);
				
				// 이미지 추가
				imageService.insert(ivo);
				
				// 찾은 부분까지 잘라내고 다시 찾기위해 저장
				tag = tag.substring(tag.indexOf("\" style=") + 9);
				
				// typeNum ++
				typeNum ++;
				
			// "img/" 문자열이 없으면 종료
			} else { // 
				break;
			}

		}
		System.out.println("bvo.boardNum: " + bvo.getBoardNum());
		return "boardPostView.do?boardNum=" + bvo.getBoardNum() + "&searchCondition=viewCnt";

	}

	// 고양이 자랑 게시판 게시글 삭제 수행 및 전체 목록으로 이동
	@RequestMapping(value = "/deleteBoard.do")
	public String deleteBoard(BoardVO bvo, Model model) {

		System.out.println("deleteBoard.do 진입");
		ImageVO ivo = new ImageVO();
		
		boardService.delete(bvo);
		
		// IMAGE 테이블에서 게시글 이미지 행 삭제
		ivo.setTargetNum(bvo.getBoardNum()); // 게시글 번호
		ivo.setTypeNum(200); // 200: 게시글
		
		imageService.delete(ivo);
		
		return "boardView.do";

	}

	// 고양이 자랑 게시글 공유하기
	@RequestMapping(value = "/shareBoard.do")
	public String shareBoard(BoardVO bvo, Model model) {

		System.out.println("shareBoard.do 진입");

		return "board_detail.jsp";

	}

	// 고양이 자랑 게시글 좋아요/취소 수행
	@ResponseBody
	@RequestMapping(value = "/updateBlike.do", method=RequestMethod.POST)
	public String updateLike(BlikeVO blvo, BoardVO bvo, Model model, HttpSession session) {
		blvo.setUserId((String) session.getAttribute("memberId"));
		bvo.setUserId((String) session.getAttribute("memberId"));
		System.out.println("updateBlike.do 진입");
		System.out.println(blvo);

		if (blvo.getUpOrDown().equals("up")) {
			System.out.println("좋아요 업");
			blikeService.insert(blvo);
		} else {
			System.out.println("좋아요 다운");
			blikeService.delete(blvo);
		}
		bvo = boardService.selectOne(bvo);
		
		return Integer.toString(bvo.getLikeCnt());

	}
}
