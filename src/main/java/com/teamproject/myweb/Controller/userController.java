package com.teamproject.myweb.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.teamproject.myweb.command.UserCheckVO;
import com.teamproject.myweb.command.UserVO;
import com.teamproject.myweb.review.boardService;

@Controller
@RequestMapping("/user")
public class userController {
	
	@Autowired
	boardService boardservice;
	
	@GetMapping("/myPage")
	public String myPage() {
		
		return "user/myPage";
	}
	
	@GetMapping("/myModify")
	public String myModify() {
		return "user/myModify";
	}
	
	@GetMapping("/userLogin")
	public String userLogin() {
		return "user/userLogin";
	}
	
	@PostMapping("/userCheck")
	public String userCheck(HttpSession session, UserCheckVO vo,Model model) {
		
		System.out.println(vo.toString());
		UserVO userVO = boardservice.userCheckes(vo);
		if(userVO == null) {
			model.addAttribute(vo);
			return "user/userLogin";
		} else {
			session.setAttribute("userVO", userVO);
			return "redirect:/main";
		}
		
	}
	
	@GetMapping("/userJoin")
	public String userJoin() {
		return "user/userJoin";
	}
	
	@GetMapping("/userFind")
	public String userFind() {
		return "user/userFind";
	}
}
