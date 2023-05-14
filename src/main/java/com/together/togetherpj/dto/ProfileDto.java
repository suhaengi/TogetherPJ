package com.together.togetherpj.dto;

import com.together.togetherpj.constant.Gender;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {

    public String profileImgName;
    public String profileImgPath;

    private String name;
    private Gender gender;
    private LocalDate birth;
    private String email;
    private LocalDate regDate;
    private Long like;

    @NotEmpty(message = "닉네임은 필수 항목 입니다.")
    private String nickname;
    private String intro;
    @NotEmpty(message = "전화번호는 필수 항목 입니다.")
    private String phone;

}
