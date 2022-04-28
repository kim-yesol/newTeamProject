package com.teamproject.myweb.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.teamproject.myweb.Service.boardService;
import com.teamproject.myweb.command.Review_uploadVO;
import com.teamproject.myweb.command.reviewVO;
import com.teamproject.myweb.util.review_Criteria;
import com.teamproject.myweb.util.review_pageVO;

@Controller
@RequestMapping("/board")
public class boardController {
	
	@Autowired
	boardService boardservice;
	
	@GetMapping("/freeBoard")
	public String freeBoard() {
		return "board/freeBoard";
	}
	
	@GetMapping("/reviewBoard")
	public String reviewBoard(Model model, review_Criteria cri) {

		
		
		cri.setLimitLeft((cri.getPage()-1)*cri.getAmount() );
		
		review_pageVO vo = new review_pageVO(cri , boardservice.reviewTotal(cri));
		
		ArrayList<reviewVO> reviewList =  boardservice.reviewList(cri);
		

		
		model.addAttribute("pageVO", vo);
		model.addAttribute("reviewVO", reviewList);
		return "board/reviewBoard";
	}
	
	@GetMapping("/reviewReg")
	public String reivewReg() {
		return "board/reviewReg";
	}
	
	@GetMapping("/reviewUpdate")
	public String reviewUpdate(@RequestParam("review_no") int review_no, Model model) {
		
		reviewVO vo =  boardservice.getDetail(review_no);
		ArrayList<Review_uploadVO> list = boardservice.getImg(review_no);
		
		model.addAttribute("UpdateVO", vo);
		model.addAttribute("Imglist", list);
		
		return "board/reviewUpdate";
	}
	
	@PostMapping("/reviewModfiy")
	public String reviewModfiy(reviewVO vo, RedirectAttributes RA) {
		System.out.println(vo.toString());
		
		int result = boardservice.updateReview(vo);
		
		if(result == 1) {
			RA.addFlashAttribute("msg", "수정성공");
		} else {
			RA.addFlashAttribute("msg", "수정실패");
		}
		
		return "redirect:/board/reviewBoard";
	}
	
	
	@PostMapping("/reviewDetele")
	public String reviewDetele(@RequestParam("review_no") int review_no,RedirectAttributes RA) {
		
		int result = boardservice.deleteReview(review_no);
		
		if(result == 1) {
			RA.addFlashAttribute("msg", "삭제성공");
		} else {
			RA.addFlashAttribute("msg", "삭제실패");
		}
		
		return "redirect:/board/reviewBoard";
	}
	
	@GetMapping("/reviewDetail")
	public String reviewdetail(@RequestParam("review_no") int review_no, Model model) {
		
		
		reviewVO vo =  boardservice.getDetail(review_no);
		ArrayList<Review_uploadVO> list = boardservice.getImg(review_no);
		
		model.addAttribute("detailVO", vo);
		model.addAttribute("Imglist", list);
		return "board/reviewDetail";
	}
	
	@PostMapping("/reviewForm")
	public String reviewform(reviewVO vo,Model model, RedirectAttributes RA,@RequestParam("file") List<MultipartFile> list) {
		
		list = list.stream().filter( f -> f.isEmpty() == false).collect( Collectors.toList());
		
		for(MultipartFile f : list) {
			if(f.getContentType().contains("image") == false) {
				RA.addFlashAttribute("msg","이미지파일을 넣으세요");
				return "redirect:/board/reviewBoard";
			}
		}
		
		int result = boardservice.reviewRegist(vo,list);
		
		if(result == 1) {
			RA.addFlashAttribute("msg", "등록성공");
		} else {
			RA.addFlashAttribute("msg", "등록실패");
		}
		
		return "redirect:/board/reviewBoard";
	}
	
	@GetMapping("/debateBoard")
	public String debateBoard() {
		return "board/debateBoard";
	}
	
	@GetMapping("/debateReg")
	public String debateReg() {
		
		return "board/debateReg";
	}
	
	
	
}
