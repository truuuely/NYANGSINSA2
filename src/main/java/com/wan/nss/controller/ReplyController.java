package com.wan.nss.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wan.nss.biz.reply.ReplyService;
import com.wan.nss.biz.reply.ReplySet;
import com.wan.nss.biz.reply.ReplyVO;


@Controller
public class ReplyController {

	@Autowired
	private ReplyService replyService;
	
	@ResponseBody
	@RequestMapping(value="/insertReply.do")
	public void insertReply(ReplyVO rvo, Model model) {
		System.out.println("insertReply 로그 "+rvo);
		// 글번호, 글쓴이, 내용, 패런트넘
		replyService.insert(rvo);
	}
	
	@RequestMapping(value="/updateReply.do")
	public String updateReply(ReplyVO rvo, Model model) {
		replyService.update(rvo);
		return "";
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteReply.do")
	public void deleteReply(ReplyVO rvo, Model model) {
		replyService.delete(rvo);
	}
	
	@RequestMapping(value="/selectAllReply.do")
	public ArrayList<ReplySet>  selectAll(ReplyVO rvo, Model model) {
		System.out.println("selectAllReply 로그 "+rvo);
		
		 
	        return replyService.selectAll(rvo);
	    }

	
	
	@RequestMapping(value="/selectOneReply.do")
	public String selectOne(ReplyVO rvo, Model model) {
		replyService.selectOne(rvo);
		return "";
	}
	
}