package com.together.togetherpj.repository;

import com.together.togetherpj.domain.Applying;
import com.together.togetherpj.dto.MyApplyResponseDTO;
import com.together.togetherpj.dto.PastAppliedDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@Log4j2
@EnableJpaAuditing
public class ApplyTests {

    @Autowired
    private RecruitRepository recruitRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ApplyingRepository applyingRepository;



    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void testApplyRecruit(){
        //ArrayList<ExDTO> list=applyingRepository.selectApplyingMember(1L);


        /*List<ExDTO> dtoList=list.stream().map(applying -> modelMapper
                .map(applying, ExDTO.class)).collect(Collectors.toList());*/

        applyingRepository.selectApplyingMember(1L).forEach(c -> {
            log.info(c.getNickname());
            log.info(c.getIsOk());
            log.info(c.getTitle());
        });


    }

    @Test
    public void testMyApply(){
        List<Applying> list=applyingRepository.myApplyingMember(4L);

        List<MyApplyResponseDTO> dtoList=list.stream().map(applying-> modelMapper
                .map(applying, MyApplyResponseDTO.class)).collect(Collectors.toList());

        log.info(dtoList);
        //System.out.println("++++++++++++++++++++++"+list.get(0).getApplier());
    }

    @Test
    public void testPastApply(){
        applyingRepository.pastApply(4L).forEach(c -> {
            log.info("+++++++"+c.getCity());
            log.info(c.getNickname());
            log.info(c.getEnddate());
            log.info(c.getId());
        });
    }

    @Test
    public void testPastAppliers(){
        applyingRepository.pastApplyw (1L).forEach(c ->{
            log.info(c.getNickname());
        });
    }
}
