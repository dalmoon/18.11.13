package com.example.demo.vo;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity(name = "REPLY")
@Data
public class Reply {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "REPLY_ID")
	private long id;
	
	@Column(name = "REPLYNUM")
	private long replynum;
	
	@Column(name = "BOARDREPLY")
	@NotBlank(message = "댓글을 작성해주세요.")
	private String boardReply;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REG_DATE")
	private Date regDate = new Date();
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WRITER")
    private Board board;
	
}
