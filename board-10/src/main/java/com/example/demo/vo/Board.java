package com.example.demo.vo;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity(name = "BOARD")
@Data
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BOARD_ID")
    private long id;

    @Column(name = "TITLE")
    @NotBlank(message = "제목을 작성해주세요.")
    private String title;

    @Column(name = "CONTENT")
    @NotBlank(message = "내용을 작성해주세요.")
    private String content;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WRITER")
    private Member member;
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy="board")
	private Collection<Reply> reply;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "REG_DATE")
    private Date regDate = new Date();
    
    @Column(name = "COUNT")
    private Integer count = 0;
    
}