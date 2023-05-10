package com.together.togetherpj.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


//이미 모집완료된 동행게시글(모집장,참여자 둘다)
public interface PastAppliedDTO {
    String getCity();
    String getNickname();
    Date getEnddate();
    Long getId();
    Long getReviewedId();
}
