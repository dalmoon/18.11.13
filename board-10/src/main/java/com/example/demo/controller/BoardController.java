package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.ReplyRepository;
import com.example.demo.vo.Board;
import com.example.demo.vo.Reply;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BoardController {
	
	@Autowired
	BoardRepository boardRepository;
	@Autowired
	ReplyRepository replyRepository;
	
	//읽기
	@GetMapping(value="/list")
	public ModelAndView list() throws Exception{
		log.debug("---->>> 읽기 동작");
		List<Board> list = boardRepository.findAll();
		return new ModelAndView("boardList", "list", list);
	}
	
	//쓰기 페이지로 이동
	@GetMapping(value="/write")
    public ModelAndView writeForm() throws Exception{
		log.debug("---->>> 쓰기페이지 동작");
        return new ModelAndView("boardWrite");
    }
	
	//쓰기
	/*@PostMapping(value="/create11")
    public ModelAndView write(Board vo) throws Exception{
		boardRepository.save(vo);
		List<Board> list = boardRepository.findAll();
        return new ModelAndView("boardList", "list", list);
    }*/
	
	//쓰기
	@PostMapping(value="/create")
	public Map<String, Object> write11(@Valid Board vo) throws Exception{
		log.debug("---->>> 쓰기 동작");
		boardRepository.save(vo);
		
		Map<String, Object> mapParam = new HashMap<String, Object>();
		
//		List<Board> list = boardRepository.findAll();
//		return new ModelAndView("boardList", "list", list);
		
		mapParam.put("result", "Y");
		
		return mapParam;
		
	}
	
	//상세보기 @RequestParam 이용한 방법
	/*@GetMapping(value="/board")
    public ModelAndView board(@RequestParam long id) throws Exception{
        Board list = boardRepository.findById(id);
		return new ModelAndView("board", "list", list);
    }*/
	
	//상세보기 @PathVariable 이용한 방법(html쪽에서 수정 해줘야함)
	@GetMapping(value="/boards/{id}")
    public ModelAndView board(@PathVariable long id) throws Exception{
		ModelAndView mav = new ModelAndView();
		log.debug("---->>> 상세 동작");
		Board list = boardRepository.findById(id);
		List<Reply> reply = replyRepository.findAllByReplynum(id);
		// System.out.println(list1);
		/*boardRepository.save(list.getCount()+1);*/
		int count = list.getCount()+1;
		list.setCount(count);
		boardRepository.save(list);
		mav.setViewName("board");
		mav.addObject("list", list);
		mav.addObject("reply", reply);
		//mav.addObject("replylist", replylist);
		return mav;
    }
	
	//수정
	@PutMapping(value="/boards/{id}")
	public ModelAndView update(@PathVariable long id, Board vo) throws Exception{
		log.debug("---->>> 수정 동작");
		boardRepository.save(vo);
		List<Board> list = boardRepository.findAll();
		String url = "redirect:/employees";

		return new ModelAndView(url, "list", list);
	}
	
	//삭제
	@RequestMapping(value="/delete/{id}")
	public ModelAndView delete(@PathVariable long id, Board vo) throws Exception{
		log.debug("---->>> 삭제 동작");
		boardRepository.delete(vo);
		List<Board> list = boardRepository.findAll();
		String url = "redirect:/employees";

		return new ModelAndView(url, "list", list);
	}
}
