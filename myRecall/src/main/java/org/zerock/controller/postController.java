package org.zerock.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.MemberVO;
import org.zerock.domain.PageCriteria;
import org.zerock.domain.RecallVO;
import org.zerock.domain.ReplyVO;
import org.zerock.service.GetRecallDataService;
import org.zerock.service.MemberService;
import org.zerock.service.ReplyService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/recallPost")
@Slf4j
public class postController {

	@Autowired
	GetRecallDataService getRecallDataService;
	@Autowired
	ReplyService replyService;
	@Autowired
	MemberService memberService;

	//리콜게시물 페이지 READ
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getRecallPost(@RequestParam("recallNum") int recallNum,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "5") int size, Model model, HttpServletRequest request,
			HttpServletResponse response) {

		// 캐시 제거
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");

		// 리콜 게시글 가져오기
		RecallVO post = getRecallDataService.getRecallPost(recallNum);
		model.addAttribute("post", post);
		// 리콜 게시글에 해당하는 댓글 리스트 가져오기
		try {
			ArrayList<ReplyVO> replyList = replyService.getReplyList(recallNum, page, size);
			PageCriteria pageCri = replyList.get(0).getPageCri();
			if (!replyList.isEmpty()) {
				model.addAttribute("replyList", replyList);
				model.addAttribute("pageCri", pageCri);
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}

		// 로그인 여부 판단하여 jsp에 전달 후 댓글등록 form 보여줄지 말지 결정
		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");
				if (memberId != null) {// 로그인 여부 판단
					// meber검색 후 memberVO 반환 
					MemberVO member = memberService.getMember(memberId);
					model.addAttribute("member", member);
				}
			return "/postPage";
	
	}
	
}
