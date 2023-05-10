package com.together.togetherpj.repository;

import com.together.togetherpj.domain.Recruit;
import com.together.togetherpj.dto.LatestRecruitDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RecruitRepositoryCustom {

/*    List<Recruit> findLatest12Recruits();

    List<Recruit> findTop12ByOrderByModDateDesc();*/

    public Page<LatestRecruitDto> getLatestRecruitDto(Pageable pageable);
}
