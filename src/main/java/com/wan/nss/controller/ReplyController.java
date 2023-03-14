package com.wan.nss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wan.nss.biz.reply.ReplyService;
import com.wan.nss.biz.reply.ReplyVO;

@Controller
public class ReplyController {

	@Autowired
	private ReplyService replyService;
	
	@RequestMapping(value="/insertReply.do")
	public String insertReply(ReplyVO rvo, Model model) {
		replyService.insert(rvo);
		return "";
	}
	
	@RequestMapping(value="/updateReply.do")
	public String updateReply(ReplyVO rvo, Model model) {
		replyService.update(rvo);
		return "";
	}
	
	@RequestMapping(value="/deleteReply.do")
	public String deleteReply(ReplyVO rvo, Model model) {
		replyService.delete(rvo);
		return "";
	}
		
	@RequestMapping(value="/selectAllReply.do")
	public String selectAll(ReplyVO rvo, Model model) {
		replyService.selectAll(rvo);
		return "";
	}
	
	@RequestMapping(value="/selectOneReply.do")
	public String selectOne(ReplyVO rvo, Model model) {
		replyService.selectOne(rvo);
		return "";
	}
	
}