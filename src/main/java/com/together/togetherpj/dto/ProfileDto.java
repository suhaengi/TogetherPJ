package com.together.togetherpj.dto;

import com.together.togetherpj.constant.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {
    private String fileName;

    private String name;
    private Gender gender;
    private Date birth;
    private String email;

    private String nickname;
    private String intro;
    private String phone;


    private LocalDate regDate;
    private Long like;

}
