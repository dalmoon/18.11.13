package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.vo.Board;

@Transactional
//@RepositoryRestResource
public interface BoardRepository extends JpaRepository<Board, Long>{
	
	Board findById(@PathVariable("id") long id);
	//Page<Board> findAll(Pageable pageable);
	Page<Board> findByTitleContaining(String search_word, Pageable pageable);
}