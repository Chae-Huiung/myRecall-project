package org.zerock.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.ReplyVO;
import org.zerock.service.ReplyService;

@Controller
@RequestMapping("/recallPost")
public class ReplyController {
	@Autowired
	ReplyService replyService;

	// 댓글 등록하기
	@RequestMapping(value = "/{recallNum}/submitReply", method = RequestMethod.POST)
	public String submitReply(@RequestParam("replyText") String replyText, @PathVariable("recallNum") int recallNum,
			HttpServletRequest request, HttpServletResponse response, RedirectAttributes rtt, Model model) {
		// 캐시 제거
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");

		HttpSession session = request.getSession(false);
		String memberId = (String) session.getAttribute("memberId");

		// 댓글등록 로직 수행
		replyService.submitReply(replyText, recallNum, memberId);
		rtt.addFlashAttribute("recallNum", recallNum);
		return "redirect:/recallPost?recallNum=" + recallNum;

	}

	// 댓글 삭제
	@RequestMapping(value = "/deleteReply", method = RequestMethod.POST)
	@ResponseBody
	public String deleteReply(@RequestBody String replyNum) {
		// 댓글 삭제 로직 수행
		int delCount = replyService.delReply(replyNum);

		return "delete reply";
	}

	// 댓글 수정
	@RequestMapping(value = "/{recallNum}/{replyNum}/updateReply", method = RequestMethod.POST)
	public String updateReply(@RequestParam("replyText") String replyText, @PathVariable("replyNum") String replyNum,
			@PathVariable("recallNum") String recallNum, HttpServletResponse response) {

		// 캐시 제거
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");
		// 댓글 수정 로직 수행
		int updatedCount = replyService.updateReply(replyNum, replyText);
		return "redirect:/recallPost?recallNum=" + recallNum;

	}
}
