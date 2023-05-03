package com.together.togetherpj.dto;

import com.together.togetherpj.constant.Gender;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
public class EditForm {
    private String fileName;

    private String name;
    private Gender gender;
    private Date birth;
    private String email;

    private String nickname;
    private String intro;
    private String phone;
    //private String sns;
    @NotBlank(message = "패스워드는 필수 항목 입니다.")
    @Length(min = 4, max=12, message = "최소 4자, 최대 12자를 입력하세요")
    private String password;

   public void change(String nickname, String intro,String phone, String password){
        this.nickname=nickname;
        this.intro=intro;
        this.phone=phone;
        this.password=password;
    }

}
