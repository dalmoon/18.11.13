package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.vo.Board;
import com.example.demo.vo.Member;
import com.example.demo.vo.Reply;

@Transactional
public interface ReplyRepository extends JpaRepository<Reply, Long>{
	
	List<Reply> findAllByReplynum(long id);

	void deleteByreplynum(long id);
}
