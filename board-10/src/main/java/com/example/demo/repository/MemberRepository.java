package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.vo.Member;

@Transactional
//@RepositoryRestResource
public interface MemberRepository extends JpaRepository<Member, String> {

	Member findByid(String id);

}
