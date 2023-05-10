package com.together.togetherpj.dto;

import com.together.togetherpj.constant.Gender;
import lombok.*;
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

    private String nickname;
    private String intro;
    private String phone;

}
