package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.repository.ReplyRepository;
import com.example.demo.vo.Board;
import com.example.demo.vo.Reply;

@RestController
public class ReplyController {
	
	@Autowired
	ReplyRepository replyRepository;
	
	/*@PostMapping(value="/replys/post")
	public Map<String, Object> insert(Reply vo) {  //throws Exception{
		Map<String, Object> mapParam = new HashMap<String, Object>();
		
		try {
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + vo.toString());
			replyRepository.save(vo);
			System.out.println(vo);
			mapParam.put("result", "Y");
			
		}catch(Exception e) {
			e.getMessage();
			e.printStackTrace();
			e.printStackTrace();
		}
		return mapParam;
		
	}*/
	
	@PostMapping(value="/replys/post")
	public Map<String, Object> write11(@Valid Reply vo) throws Exception{
		
		replyRepository.save(vo);
		
		Map<String, Object> mapParam = new HashMap<String, Object>();
		
//		List<Board> list = boardRepository.findAll();
//		return new ModelAndView("boardList", "list", list);
		
		mapParam.put("result", "Y");
		
		return mapParam;
	}
	
	/*@GetMapping(value="/replys")
    public ModelAndView board() throws Exception{
		List<Reply> list = replyRepository.findAll();
		//Reply Replylist = replyRepository.findByboard(id);
		// System.out.println(list1);
		boardRepository.save(list.getCount()+1);
		return new ModelAndView("board", "replylist", list);
    }*/
	
	@GetMapping(value="/replys")
    public ModelAndView board() throws Exception{
		List<Reply> list = replyRepository.findAll();
		//Reply Replylist = replyRepository.findByboard(id);
		System.out.println("safsafsafsaf");
		return new ModelAndView("board", "replylist", list);
    }
	
	@RequestMapping(value="/replydelete/{id}")
	public ModelAndView delete(@PathVariable long id, Reply vo) throws Exception{
		replyRepository.deleteByreplynum(id);
		String url = "redirect:/boards/"+id;
		return new ModelAndView(url);
	}
	/*@GetMapping(value = "/replys")
	@ResponseBody
	public ResponseEntity<List<Reply>> replys()throws Exception{
		ResponseEntity<List<Reply>> entity =null;
		List<Reply> list = replyRepository.findAll();
		entity =new ResponseEntity<List<Reply>>(list, HttpStatus.OK);
		return entity;
	}*/
	
	/*@GetMapping(value="/replys")
	public ModelAndView list() throws Exception{
		List<Reply> list = replyRepository.findAll();
		String url = "redirect:/employees";

		return new ModelAndView(url, "list", list);
	}*/
}
