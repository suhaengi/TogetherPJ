package com.together.togetherpj.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class ReviewFormDTO {
    @NotEmpty
    private String comment;

    private Long rid;

    private Long reviewedId;

    private boolean like;







}
