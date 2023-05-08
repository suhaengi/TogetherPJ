package com.together.togetherpj.dto;

import com.together.togetherpj.constant.State;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@ToString
public class ViewForm {

    private Long bno;

    private LocalDateTime regDate;

    private LocalDateTime modDate;

    private String writer;

    private String content;

    private String title;

    private String city;

    private String tourspot;

    private long perNum;

    private long curNum;

    private LocalDate startdate;

    private LocalDate enddate;

    private State state;

    private long viewcount;


}
