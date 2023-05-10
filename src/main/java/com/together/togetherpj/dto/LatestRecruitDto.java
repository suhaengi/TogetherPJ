package com.together.togetherpj.dto;

import com.together.togetherpj.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LatestRecruitDto {
    private Long id;
    private String title;
    private String nickname;
    private LocalDate startdate;
    private LocalDate enddate;
    private String city;
}