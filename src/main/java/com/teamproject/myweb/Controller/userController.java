package com.teamproject.myweb.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.teamproject.myweb.Service.boardService;
import com.teamproject.myweb.command.UserCheckVO;
import com.teamproject.myweb.command.UserVO;
import com.teamproject.myweb.user.UserService;

@Controller
@RequestMapping("/user")
public class userController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/myPage")
	public String myPage(@RequestParam("user_no") int user_no,
						 Model model) {
		
		UserVO userVO = userService.myPage(user_no);
		model.addAttribute("userVO", userVO);
		
		return "user/myPage";
	}
	
	@GetMapping("/myModify")
	public String myModify() {
		
		
		return "user/myModify";
	}
	
	@PostMapping("/modify")
	public String modify(UserVO vo) {
		System.out.println(vo.toString());
		userService.modify(vo);
		
		return "redirect:/main";
	}
	
	@GetMapping("/userLogin")
	public String userLogin() {
		return "user/userLogin";
	}
	
	@PostMapping("/userCheck")
	public String userCheck(HttpSession session, UserCheckVO vo,Model model) {
		
		System.out.println(vo.toString());
		UserVO userVO = userService.userCheckes(vo);
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
	
	@PostMapping("/joinform")
	public String joinform(UserVO vo) {
		userService.join(vo);
		return "redirect:/user/userLogin";
	}
	
	@GetMapping("/userFind")
	public String userFind() {
		return "user/userFind";
	}
	
	@PostMapping("/userDelete")
	public String userDelete(@RequestParam("user_no") int user_no, RedirectAttributes RA) {
		
		int result = userService.userDelete(user_no);
		
		if(result == 1) {
			RA.addFlashAttribute("msg", "탈퇴 되었습니다");
		} else {
			RA.addFlashAttribute("msg", "탈퇴에 실패 했습니다");
		}
		
		return "redirect:/main";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/main";
	}
	
}
