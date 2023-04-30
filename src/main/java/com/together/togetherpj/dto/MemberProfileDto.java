package com.together.togetherpj.dto;

import com.together.togetherpj.constant.Gender;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder @Getter @Setter @ToString
public class MemberProfileDto {
  private String nickname;
  private String ageGroup;
  private Gender gender;
}
