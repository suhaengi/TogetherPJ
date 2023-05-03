package com.together.togetherpj.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@ToString(exclude = "member")
public class ProfileImg {
    @Id
    private String uuid;

    private String fileName;

    private int ord;

 /*   @OneToOne
    private Member member;

    public void changeMember(Member member){
        this.member = member;
    }*/
}
