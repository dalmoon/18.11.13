package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.repository.BoardRepository;
import com.example.demo.vo.Board;

@Controller
public class EmployeeController {
	
	@Autowired
	BoardRepository repository;
	
	@GetMapping("/employees")
//	  public String getEmployees(@RequestParam(value="search_word", required =false, defaultValue="")  String search_word, @PageableDefault(size = 10, sort = "id") Pageable pageable,
	  public String getEmployees(String search_word, @PageableDefault(size = 10, sort = "id") Pageable pageable,
	                             Model model) {
		//search_word = "";
		
		
		if(search_word == null) {
			search_word = "";
		}
		
		Page<Board> page = repository.findByTitleContaining(search_word, pageable);
		//Page<Board> page = repository.findAll(pageable); 
	      
		List<Sort.Order> sortOrders = page.getSort().stream().collect(Collectors.toList());
	      if (sortOrders.size() > 0) {
	          Sort.Order order = sortOrders.get(0);
	          model.addAttribute("sortProperty", order.getProperty());
	          model.addAttribute("sortDesc", order.getDirection() == Sort.Direction.DESC);
	      }
	      model.addAttribute("page", page);
	      return "employee-page";
	  }
	
}
