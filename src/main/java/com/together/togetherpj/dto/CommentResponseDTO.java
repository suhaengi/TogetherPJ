package com.together.togetherpj.dto;

import java.time.LocalDate;
import java.util.Date;

public interface CommentResponseDTO {
    String getNickname();
    String getContent();
    Date getModdate();
}
