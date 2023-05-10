package com.together.togetherpj.dto;

import com.together.togetherpj.domain.Recruit;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
public class RecruitBoardDto {
    private Long id;
    private String title;
    private Date startdate;
    private Date enddate;
    private long viewcount;

    public Recruit toEntity(){
        Recruit build = Recruit.builder()
                .id(id)
                .title(title)
                .startdate(startdate)
                .enddate(enddate)
                .viewcount(viewcount)
                .build();

        return build;
    }

    @Builder
    public RecruitBoardDto(Long id, String title, Date startdate, Date enddate, long viewcount){
        this.id = id;
        this.title = title;
        this.startdate = startdate;
        this.enddate = enddate;
        this.viewcount = viewcount;
    }
}
