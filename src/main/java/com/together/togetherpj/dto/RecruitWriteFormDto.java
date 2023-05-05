package com.together.togetherpj.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter @Setter @ToString
public class RecruitWriteFormDto {

  @NotEmpty(message = "제목은 필수 항목 입니다.")
  private String title;

  @NotEmpty(message = "도시는 필수 항목 입니다.")
  private String city;

  @NotEmpty(message = "총원을 입력해주세요" )
  private String perNum;

  @NotEmpty(message = "출발 날짜를 입력해주세요" )
  private String startdate;

  @NotEmpty(message = "도착 날짜를 입력해주세요")
  private String enddate;

  @NotEmpty(message = "내용을 입력해주세요" )
  private String content;
}
