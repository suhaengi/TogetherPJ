package com.together.togetherpj.dto;

import com.together.togetherpj.constant.Gender;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import java.time.LocalDate;

@Data
@Builder
public class PwForm {
    @Length(min = 4, max=12, message = "최소 4자, 최대 12자를 입력하세요")
    private String password;


}
