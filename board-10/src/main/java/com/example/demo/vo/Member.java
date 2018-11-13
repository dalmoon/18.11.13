package com.example.demo.vo;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity(name = "MEMBER")
@Data
public class Member {
	
	@Id
    @Column(name = "UUID")
    private String uuid;
	
	@Column(name = "ID", unique = true)
	@NotBlank(message = "아이디를 작성해주세요.")
    private String id;

    @Column(name = "PASSWORD")
    @NotBlank(message = "비밀번호를 작성해주세요.")
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "REG_DATE")
    private Date regDate = new Date();

    @OneToMany(cascade=CascadeType.ALL, mappedBy="member")
    private Collection<Board> board;
    
}