package com.together.togetherpj.dto;

import com.together.togetherpj.constant.Gender;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import java.time.LocalDate;

@Data
@Builder
public class EditForm {
    private String fileName;

    private String name;
    private Gender gender;
    private LocalDate birth;
    private String email;

    private String nickname;
    private String intro;
    private String phone;
    //private String sns;

    @Length(min = 4, max=12, message = "최소 4자, 최대 12자를 입력하세요")
    private String password;


}
