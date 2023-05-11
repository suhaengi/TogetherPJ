package com.together.togetherpj.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Getter @Setter @ToString
public class RecruitWriteFormDto {

  private Long bno;

  @NotEmpty(message = "제목은 필수 항목 입니다.")
  private String title;

  @NotEmpty(message = "도시는 필수 항목 입니다.")
  private String city;

  @Positive(message = "총원을 입력해주세요" )
  private long perNum;

  @NotEmpty(message = "출발 날짜를 입력해주세요" )
  private String startdate;

  @NotEmpty(message = "도착 날짜를 입력해주세요")
  private String enddate;

  @NotEmpty(message = "내용을 입력해주세요" )
  private String content;

  MultipartFile imgFile;

}
