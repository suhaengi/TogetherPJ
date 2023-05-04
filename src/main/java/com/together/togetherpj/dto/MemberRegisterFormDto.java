package com.together.togetherpj.dto;

import com.together.togetherpj.constant.Gender;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter @Setter @ToString
public class MemberRegisterFormDto {
  @NotBlank(message = "패스워드는 필수 항목 입니다.")
  // @Length(min = 10, max=20, message = "최소 10자, 최대 20자를 입력하세요")
  @Length(min = 4, max=12, message = "최소 4자, 최대 12자를 입력하세요")
//  @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[*!@$%^&(){}\\[\\]:;<>,.?/~_+\\-=|])[A-Za-z\\d@$!%*?&]{4,12}$",
//      message = "패스워드는 4~12자리, 숫자, 영소문자, 영대문자, 특수문자(*!@$%^&(){}[]:;<>,.?/~_+-=|\\)를 포함해야 합니다.")
  private String password;

  @NotEmpty(message = "이름은 필수 항목 입니다.")
  private String Name;

  @Enumerated(EnumType.STRING)
  private Gender gender;

  @NotEmpty(message = "전화번호는 필수 항목 입니다.")
  private String phone;

  @NotEmpty(message = "이메일은 필수 항목 입니다.")
  @Email(message = "이메일 형식으로 입력하세요.")
  private String email;

  @NotEmpty(message = "생일은 필수 항목 입니다.")
  private String birth;

  @NotEmpty(message = "닉네임은 필수 항목 입니다.")
  private String nickname;
}
