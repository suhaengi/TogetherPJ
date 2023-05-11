package com.together.togetherpj.dto;

//내가 현재 참여하는 동행그룹dto
public interface ApplyingResponseDTO {
   //@Value("#{target.isOk}")
    boolean getIsOk();
    //@Value("#{target.title}")
    String getTitle();
    //@Value("#{target.nickname}")
    String getNickname();

    String getRid();
    Long getAid();



}
