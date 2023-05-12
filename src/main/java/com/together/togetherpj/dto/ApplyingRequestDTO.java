package com.together.togetherpj.dto;

import com.together.togetherpj.constant.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplyingRequestDTO {
    private State state;
    private Long rid;
    private Long aid;
}
