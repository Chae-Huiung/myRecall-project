package org.zerock.controller;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.zerock.domain.CarVO;
import org.zerock.domain.MemberVO;
import org.zerock.domain.PageCriteria;
import org.zerock.domain.RecallVO;
import org.zerock.mapper.SelectionMapper;
import org.zerock.service.GetRecallDataService;
import org.zerock.service.GetRecallDataServiceImpl;
import org.zerock.service.MemberService;
import org.zerock.service.MemberServiceImpl;
import org.zerock.service.SearchService;
import org.zerock.service.SelectionService;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/")
@Slf4j
public class MainPageController {

	@Autowired
	SelectionService selectionService;
	@Autowired
	GetRecallDataServiceImpl dataServiceImpl;
	@Autowired
	SearchService searchService;
	@Autowired
	MemberService memberService;

//메인페이지 
	@GetMapping("/mainPage")
	public String showMainPage(Model model, HttpServletRequest request, HttpServletResponse response) {

		// 캐시 제거
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");

		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");
		model.addAttribute("memberId", memberId);
		// db에서 제조사 리스트 검색 후 Model 객체에 담아 뷰에 반환
		model.addAttribute("brands", selectionService.selectBrands());

		return "/mainPage";
	}

//메인페이지에서 브랜드 selection 선택되었을 때 서버에서 car selection의 option으로 제공될 차량목록 반환 
	@GetMapping("/selection/car")
	public ResponseEntity<String> selectCars(@RequestParam("brand") String brand) {

		ObjectMapper objectMapper = new ObjectMapper();
		String brands;
		try {
			brands = objectMapper.writeValueAsString(selectionService.selectCars(brand));
			HttpHeaders header = new HttpHeaders();
			header.add("Content-type", "text/pln;charset=utf-8");
			ResponseEntity<String> response = new ResponseEntity<String>(brands, header, HttpStatus.OK);
			return response;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

//car selection에서 선택된 차량의 생상년도 리스트 반환
	@GetMapping("/selection/year")
	@ResponseBody
	public ArrayList<CarVO> selectYear(@RequestParam("brand") String brand, @RequestParam("car") String car) {
		ArrayList<CarVO> cars = selectionService.selectYears(brand, car);
		return cars;
	}

//selection 선택 후 검색 버튼 클릭 시 검색결과 list 페이지 반환 
	@RequestMapping(value = "/searchBySelection", method = RequestMethod.GET)
	public String selectRecallList(@RequestParam("brand") String brand, @RequestParam("car") String carName,
			@RequestParam("year") String fromDate, @RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "10") int size, Model model,HttpServletRequest request, HttpServletResponse response) {

		// 캐시 제거
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");
		
//		//로그인된 회원 반환 
				HttpSession session = request.getSession();
				String memberId = (String) session.getAttribute("memberId");
				if (memberId != null) {// 로그인 여부 판단
					// meber검색 후 memberVO 반환 
					MemberVO member = memberService.getMember(memberId);
					model.addAttribute("member", member);
				}

		// db에서 제조사 리스트 검색 후 Model 객체에 담아 뷰에 반환
		model.addAttribute("brands", selectionService.selectBrands());

		// 검색 결과인 리콜 게시글들을 리스트로 model 객체에 담아 뷰에 반환
		ArrayList<RecallVO> posts = dataServiceImpl.getRecallPosts(brand, carName, fromDate, page, size);
		PageCriteria pageCri = posts.get(0).getPageCri();
		model.addAttribute("posts", posts);
		model.addAttribute("pageCri", pageCri);

		return "/recallListPage";
	}

//검색창으로 검색 시 검색결과 list 반환
	@RequestMapping(value = "/searchByStr", method = RequestMethod.GET)
	public String RecallListBySearch(@RequestParam("searchStr") String searchStr,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "10") int size, Model model,HttpServletRequest request ,HttpServletResponse response) {

		// 캐시 제거
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");
		
		//로그인된 회원 반환 
				HttpSession session = request.getSession();
				String memberId = (String) session.getAttribute("memberId");
				if (memberId != null) {// 로그인 여부 판단
					// meber검색 후 memberVO 반환 
					MemberVO member = memberService.getMember(memberId);
					model.addAttribute("member", member);
				}

		// db에서 제조사 리스트 검색 후 Model 객체에 담아 뷰에 반환
		model.addAttribute("brands", selectionService.selectBrands());
		
		ArrayList<RecallVO> posts = searchService.getRecallPosts(searchStr, page, size);
		if(!posts.isEmpty()){
			PageCriteria pageCri = posts.get(0).getPageCri();
			model.addAttribute("posts", posts);
			model.addAttribute("pageCri", pageCri);
		}
		return "/recallListPage";
	}

}
