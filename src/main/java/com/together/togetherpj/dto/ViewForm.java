package com.together.togetherpj.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ViewForm {

    private LocalDateTime regDate;

    private LocalDateTime modDate;

    private String writer;

    private long curNum;

    private long viewcount;

    private String title;

    private String city;

    private String perNum;

    private String startdate;

    private String enddate;

    private String content;
}
