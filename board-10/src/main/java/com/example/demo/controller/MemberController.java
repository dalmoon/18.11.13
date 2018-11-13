package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.vo.Board;
import com.example.demo.vo.Member;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MemberController {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private BoardRepository boardRepository;
	
	
	//메인
	@GetMapping(value="/members")
	public ModelAndView main() {
		return new ModelAndView("login");
	}
	
	//회원가입화면
	@GetMapping(value="/members/post")
	public ModelAndView join() {
		return new ModelAndView("join");
	}
	
	//회원가입
	@PostMapping(value="/members/post")
	public ModelAndView joinForm(@Valid Member vo) throws Exception{
		log.debug("---->>> 회원가입 동작");
		//		vo.setPassword(bCryptPasswordEncoder.encode(vo.getPassword()));
		vo.setUuid(String.valueOf(UUID.randomUUID()));
		memberRepository.save(vo);
		return new ModelAndView("login");
    }
	
	//로그인
	@PostMapping(value="/members/login")
	public ModelAndView login(String id, String password, HttpSession session) throws Exception{
		log.debug("---->>> 로그인 동작");
		Member vo = memberRepository.findByid(id);
		/*if(vo.get().getId() == null) {
			return new ModelAndView("login");
		}
		if(!password.equals(vo.get().getPassword())) {
			return new ModelAndView("login");
		}*/
		session.setAttribute("member", vo);
		List<Board> list = boardRepository.findAll();
		
//		return new ModelAndView("boardList", "list", list);
		String url = "redirect:/employees";

		return new ModelAndView(url, "list", list);
		
	}
	
	@GetMapping(value="/logout")
	public ModelAndView logout(HttpSession hs)throws Exception{
		log.debug("---->>> 로그아웃 동작");
		hs.invalidate();
		return new ModelAndView("login");
	}
}
