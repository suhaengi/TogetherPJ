package com.together.togetherpj.dto;

import com.together.togetherpj.constant.Gender;
import com.together.togetherpj.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {
    private Long fileId;

    private String name;
    private Gender gender;
    //private Date birth;
    private String email;

    private String nickname;
    private String intro;
    private String phone;


    private LocalDate regDate;
    private Long like;

    /*
    public static ProfileDto from(Member member){
        return new ProfileDto(member);
    }

    protected ProfileDto (Member member){
        this.name= Optional.ofNullable(member.getName()).orElse(null);
        this.gender= Optional.ofNullable(member.getGender()).orElse(null);
        //this.birth= Optional.ofNullable(member.getBirth()).orElse(null);
        this.email= Optional.ofNullable(member.getEmail()).orElse(null);
        this.nickname= Optional.ofNullable(member.getNickname()).orElse(null);
        this.intro= Optional.ofNullable(member.getIntro()).orElse(null);
        this.phone= Optional.ofNullable(member.getPhone()).orElse(null);
        this.regDate= Optional.ofNullable(member.getJoinDate()).orElse(null);
        this.like= Optional.ofNullable(member.getLike()).orElse(null);
        this.profileImage= Optional.ofNullable(member.getImage()).orElse(null);
    }
*/
}
