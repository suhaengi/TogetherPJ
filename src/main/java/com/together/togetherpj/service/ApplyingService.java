package com.together.togetherpj.service;

import com.together.togetherpj.domain.Applying;
import com.together.togetherpj.domain.id.ApplyingId;
import com.together.togetherpj.repository.ApplyingRepository;
import com.together.togetherpj.repository.MemberRepository;
import com.together.togetherpj.repository.RecruitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ApplyingService {
    private final MemberRepository memberRepository;
    private final RecruitRepository recruitRepository;
    private final ApplyingRepository applyingRepository;

    public void findById(){
        ApplyingId findId = new ApplyingId(1L, 3L);
        Optional<Applying> findApplying = applyingRepository.findById(findId);
        log.info("findApplying= {}", findApplying);
    }
}
