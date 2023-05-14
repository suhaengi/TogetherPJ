package com.together.togetherpj.controller;

import com.together.togetherpj.constant.State;
import com.together.togetherpj.domain.Member;
import com.together.togetherpj.domain.Recruit;
import com.together.togetherpj.dto.*;
import com.together.togetherpj.img.ImgService;
import com.together.togetherpj.repository.RecruitRepository;
import com.together.togetherpj.service.CommentService;
import com.together.togetherpj.service.MemberService;
import com.together.togetherpj.service.RecruitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/recruit")
@Slf4j
@RequiredArgsConstructor
public class RecruitController {
  private final RecruitService recruitService;
  private final ImgService imgService;
  private final CommentService commentService;

  //게시글 작성페이지
  @GetMapping("/write-form")
  public String register(Model model) {
    model.addAttribute("writeFormDto", new RecruitWriteFormDto());
    return "recruit/write-form";
  }

  //게시글 저장
  @PostMapping("/save")
  public String recruitSave(@Valid RecruitWriteFormDto writeFormDto,
      BindingResult bindingResult, Authentication authentication, Model model) {

    if (bindingResult.hasErrors()) {
      return "recruit/write-form";
    }

    try {
      recruitService.save(writeFormDto, authentication.getName());
    } catch (IllegalStateException e) {
      model.addAttribute("errorMessage", e.getMessage());
      return "recruit/write-form";
    }

    return "redirect:/";
  }


  //게시글 보기, 수정 페이지, 댓글 보이기
  @GetMapping({"/view", "/modify"})
  public void read( Long bno, Model model) throws IOException {
    ViewForm dto = recruitService.readOne(bno);
    model.addAttribute("dto", dto);

    List<CommentResponseDTO> list=commentService.selectComment(bno);
    log.info("-------------------------");

    model.addAttribute("commentDto", list);
  }

  //동행 신청하기 버튼
  @PostMapping("/apply")
  public String applying(Authentication authentication,Long bno){
    if(authentication == null){
      return "redirect:/member/login";
    }

    String email = authentication.getName();

    recruitService.Applying(email,bno);
    return "redirect:/";
  }

  //이미지 불러오기
/* @GetMapping("/image")
  public ResponseEntity<?> getProfileImg (Long bno) throws IOException {
    ViewForm dto = recruitService.readOne(bno);

    InputStream inputStream = new FileInputStream(dto.getImgPath());
    byte[] imageByteArray = IOUtils.toByteArray(inputStream);
    inputStream.close();
    return new ResponseEntity<>(imageByteArray, HttpStatus.OK);
  }*/

  //게시글 수정
  @PostMapping("/modify")
  public String modify(Long bno,RecruitWriteFormDto view,RedirectAttributes redirectAttributes){
      recruitService.change(bno,view);
    redirectAttributes.addAttribute("bno",bno);
      return "redirect:/recruit/view";
  }
  
  //게시글 삭제
  @PostMapping("/delete")
  public String delete(Long bno){
    recruitService.delete(bno);
      return "redirect:/";
  }



  //코멘트 달기
  @PostMapping("/createComment")
  public String createCom(@Valid CommentRequestDTO commentRequestDTO,
                              BindingResult bindingResult, Authentication authentication, Model model
  ,RedirectAttributes redirectAttributes){

    if (bindingResult.hasErrors()) {
      return "recruit/view";
    }

    try {
     /* log.info(commentRequestDTO.getBno().toString());
      log.info(commentRequestDTO.getReply());*/
      commentService.createComment(authentication, commentRequestDTO);

    } catch (IllegalStateException e) {
      model.addAttribute("errorMessage", e.getMessage());
      return "recruit/view";
    }
    redirectAttributes.addAttribute("bno",commentRequestDTO.getBno());
    return "redirect:/recruit/view";
  }

}
