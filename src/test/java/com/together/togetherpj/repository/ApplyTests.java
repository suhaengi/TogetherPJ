package com.together.togetherpj.repository;

import com.together.togetherpj.constant.State;
import com.together.togetherpj.domain.Applying;
import com.together.togetherpj.domain.Member;
import com.together.togetherpj.domain.Recruit;
import com.together.togetherpj.domain.Review;
import com.together.togetherpj.domain.id.ApplyingId;
import com.together.togetherpj.dto.MyApplyResponseDTO;
import com.together.togetherpj.dto.PastAppliedDTO;
import com.together.togetherpj.dto.RecruitWriteFormDto;
import com.together.togetherpj.dto.ReviewFormDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.together.togetherpj.constant.State.RECRUITING;

@SpringBootTest
@Log4j2

public class ApplyTests {

    @Autowired
    private RecruitRepository recruitRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ApplyingRepository applyingRepository;

    @Autowired
    private ReviewRepository reviewRepository;

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

       /* List<MyApplyResponseDTO> dtoList=list.stream().map(applying-> modelMapper
                .map(applying, MyApplyResponseDTO.class)).collect(Collectors.toList());*/

        log.info(list);
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
        applyingRepository.pastAppliedReview (5L, 1L).forEach(c ->{
            log.info(c.getReviewedId());
        });
    }

    @Test
    public void testpostReview(){


       /* Review review=createReview()
        reviewRepository.save()*/
    }

    @Test
    void createReview(){

        Recruit recruit=recruitRepository.findById(1L)
                .orElseThrow(IllegalStateException::new);
        //작성당하는사람
        Member reviewed=memberRepository.findById(1L).orElseThrow(IllegalStateException::new);
        //작성하는 사람
        Member writer=memberRepository.findById(4L).orElseThrow(IllegalStateException::new);
        Applying applying=Applying.builder()
                .id(new ApplyingId(4L,1L))
                .isOk(true)
                .applier(reviewed)
                .recruit(recruit)
                .build();



        Review review= Review.builder()
                .comment("you are good")
                .reviewer(writer)
                .applying(applying)
                .build();

        reviewRepository.save(review);
    }

    @Test
    void aa(){

        log.info(applyingRepository.findById(new ApplyingId(1L,1L)));
    }

    @Test
    private Member createMember(Authentication authentication){
        String email=authentication.getName();
        Member member=memberRepository.findByEmail(email).orElseThrow(()->{
            throw new UsernameNotFoundException("아이디 비번 잘못됐습니다");
        });

        return member;
    }

    @Test
    public void findId(){
        Long rid=1L;
       Recruit recruit=recruitRepository.findById(rid)
               .orElseThrow(IllegalStateException::new);

       log.info(recruit.getCity());
    }

    @Test
    @Transactional
    public void save(){
        Long mid=4L;
        Member writer = memberRepository.findById(mid)
                .orElseThrow(IllegalStateException::new);

        Recruit recruit = Recruit.builder()
                .id(6L)
                .recruitWriter(writer)
                .city("safas")
                .curNum(3)
                .perNum(6)
                .state(RECRUITING)
                .build();
        Applying applying = createWriterApplying(writer, recruit);

        applyingRepository.save(applying);
        recruitRepository.save(recruit);
    }

    private Applying createWriterApplying(Member writer, Recruit recruit) {
        Applying applying = new Applying();
        applying.setOk(true);
        applying.setRecruit(recruit);
        applying.setApplier(writer);

        return applying;
    }

    private Recruit createRecruit(RecruitWriteFormDto writeFormDto, Member writer){
        return Recruit.builder()
                .title(writeFormDto.getTitle())
                .city(writeFormDto.getCity())
                .content(writeFormDto.getContent())
                .perNum(writeFormDto.getPerNum())
                .startdate(LocalDate.parse(writeFormDto.getStartdate()))
                .enddate(LocalDate.parse(writeFormDto.getEnddate()))
                .state(RECRUITING)
                .recruitWriter(writer)
                .build();
    }


}
