package com.teamproject.myweb.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class boardController {
	
	@GetMapping("/freeBoard")
	public String freeBoard() {
		return "board/freeBoard";
	}
	
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
