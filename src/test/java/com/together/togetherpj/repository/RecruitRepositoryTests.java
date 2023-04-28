package com.together.togetherpj.repository;

import com.together.togetherpj.constant.State;
import com.together.togetherpj.domain.Member;
import com.together.togetherpj.domain.Recruit;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.util.Optional;

@SpringBootTest
@Log4j2
@EnableJpaAuditing
public class RecruitRepositoryTests {

    @Autowired
    private RecruitRepository recruitRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Test
    public void testInsert() throws ParseException{

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");


        Recruit recruit = Recruit.builder()
                .title("recruit test")
                .city("yangyang")
                .content("양양갈사람")
                .per_num(3)
                .writer(memberRepository.findByEmail("pw1234@pw").getNickname())
                .startdate(formatter.parse("2023-04-29"))
                .enddate(formatter.parse("2023-04-30"))
                .build();

        Recruit result = recruitRepository.save(recruit);
    }

    @Test
    public void testSelect(){
        Long cno = 1L;
        Optional<Recruit> result = recruitRepository.findById(cno);
        Recruit recruit = result.orElseThrow();
        log.info(recruit);
    }


}
