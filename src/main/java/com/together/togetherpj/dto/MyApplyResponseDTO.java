package com.together.togetherpj.dto;

import com.together.togetherpj.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//내가 모집장인 동행 dto
public class MyApplyResponseDTO {
    private String title;//recruit
    private long per_num;//recruit
    private long cur_num;//recruit
    private String nickname;//member

}
