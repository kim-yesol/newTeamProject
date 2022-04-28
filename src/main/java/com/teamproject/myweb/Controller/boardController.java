package com.teamproject.myweb.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.teamproject.myweb.command.CommentVO;
import com.teamproject.myweb.command.freeBoardVO;
import com.teamproject.myweb.comment.CommentService;
import com.teamproject.myweb.freeBoard.FreeBoardService;
import com.teamproject.myweb.util.Criteria;
import com.teamproject.myweb.util.PageVO;

@Controller
@RequestMapping("/board")
public class boardController {
	
	@Autowired
	@Qualifier("freeboardService")
	private FreeBoardService freeBoardService; 
	
	@Autowired
	@Qualifier("CommentService")
	private CommentService commentService;
	
	
	@GetMapping("/freeBoard")
	public String freeBoard(Model model, Criteria cri, RedirectAttributes RA) {
		
		PageVO pageVO = new PageVO(cri, freeBoardService.getTotal(cri));
		cri.setLeftpage((cri.getPage() -1) * cri.getAmount());
		ArrayList<freeBoardVO> list = freeBoardService.getList(cri);
		
		if(freeBoardService.getTotal(cri) == 0) {
			RA.addFlashAttribute("msg", "검색 결과가 없습니다");
			return "redirect:/board/freeBoard";
		}
		
		model.addAttribute("list", list);
		model.addAttribute("pageVO", pageVO);
		return "board/freeBoard";
	}
	
	@PostMapping("/freeRegForm")
	public String freeRegForm(freeBoardVO vo, RedirectAttributes RA) {
		
		
		int result = freeBoardService.regist(vo);
		
		if(result == 1) {
			RA.addFlashAttribute("msg", "게시글이 등록되었습니다");
		} else {
			RA.addFlashAttribute("msg", "게시글 등록에 실패했습니다");
		}
		
		
		return "redirect:/board/freeBoard";
	}
	
	@GetMapping("/freeBoardReg")
	public String freeBoardReg() {
		return "board/freeBoardReg";
	}
	
	@GetMapping("/freeBoardDetail")
	public String freeBoardDetail(@RequestParam("free_bno") int free_bno, Model model) {
		
		freeBoardVO detail = freeBoardService.getDetail(free_bno);
		ArrayList<CommentVO> commentVO = commentService.commentList(free_bno);
		model.addAttribute("detail", detail);
		model.addAttribute("commentVO", commentVO);
		return "board/freeBoardDetail";
	}
	
	@GetMapping("/freeBoardUpdate")
	public String freeBoardUpdate(@RequestParam("free_bno") int free_bno, Model model) {
			
		freeBoardVO detail = freeBoardService.getDetail(free_bno);
		model.addAttribute("detail", detail);
		
		return "board/freeBoardUpdate";
	}
	@PostMapping("/freeBoardUpdateForm")
	public String freeBoardUpdateForm(freeBoardVO vo,
								  RedirectAttributes RA) {
			
		int result = freeBoardService.update(vo);
		
		if(result == 1) {
			RA.addFlashAttribute("msg", "게시글이 수정되었습니다");
		}else {
			RA.addFlashAttribute("msg", "게시글 수정에 실패했습니다. 관리자에게 문의하세요");
		}
		
		return "redirect:/board/freeBoard";
	}
	
	@PostMapping("/freeBoardDelete")
	public String freeBoardDelete(@RequestParam("free_bno") int free_bno,
								  RedirectAttributes RA) {
		System.out.println(free_bno);
		int result = freeBoardService.delete(free_bno);
		
		if(result == 1) {
			RA.addFlashAttribute("msg", "게시글이 삭제되었습니다");
		} else {
			RA.addFlashAttribute("msg", "게시글 삭제에 실패했습니다. 관리자에게 문의하세요");
		}
		return "redirect:/board/freeBoard";
	}
	
	
	
	
	//---------------------------------------------
	@GetMapping("/reviewBoard")
	public String reviewBoard() {
		return "board/reviewBoard";
	}
	
	@GetMapping("/reviewReg")
	public String reivewReg() {
		return "board/reviewReg";
	}
	
	@GetMapping("/reviewDetail")
	public String reviewdetail() {
		return "board/reviewDetail";
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
