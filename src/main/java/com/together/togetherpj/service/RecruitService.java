package com.together.togetherpj.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.together.togetherpj.constant.State;
import com.together.togetherpj.domain.Applying;
import com.together.togetherpj.domain.Member;
import com.together.togetherpj.domain.Recruit;
import com.together.togetherpj.domain.id.ApplyingId;
import com.together.togetherpj.dto.RecruitWriteFormDto;
import com.together.togetherpj.dto.ViewForm;
import com.together.togetherpj.img.ImgService;
import com.together.togetherpj.repository.ApplyingRepository;
import com.together.togetherpj.repository.MemberRepository;
import com.together.togetherpj.repository.RecruitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor

public class RecruitService {
  private final RecruitRepository recruitRepository;
  private final MemberRepository memberRepository;
  private final ApplyingRepository applyingRepository;
  private final ImgService imgService;
  private final AmazonS3Client amazonS3Client;

  @Transactional
  public ViewForm readOne(Long bno) throws IOException {
    Recruit recruit = recruitRepository.findById(bno).orElseThrow();
    recruit.setViewcount(recruit.getViewcount() + 1L);
    Member member = recruit.getRecruitWriter();
    ViewForm viewForm = ViewForm.builder()
            .bno(bno)
            .regDate(recruit.getRegDate())
            .modDate(recruit.getModDate())
            .writer(member.getNickname())
            .content(recruit.getContent())
            .title(recruit.getTitle())
            .city(recruit.getCity())
            .perNum(recruit.getPerNum())
            .curNum(recruit.getCurNum())
            .startdate(recruit.getStartdate())
            .enddate(recruit.getEnddate())
            .state(recruit.getState())
            .viewcount(recruit.getViewcount())
            .writerId(member.getEmail())
            .imgName(recruit.getImgName())
            .imgPath(recruit.getImgPath())
            .build();
    return viewForm;
  }

  //게시글 편집
  public void change(Long bno, RecruitWriteFormDto dto){
    Recruit recruit = recruitRepository.findById(bno).orElseThrow();
    recruit.setTitle(dto.getTitle());
    recruit.setContent(dto.getContent());

    MultipartFile imgFile = dto.getImgFile();
    try{
      upload(imgFile, "/recruit",recruit);
    }catch(IOException e){
    }
  recruitRepository.save(recruit);
  }

  public void delete(Long bno){
    recruitRepository.deleteById(bno);
  }
  public List<Recruit> findAll(){
    return recruitRepository.findAll();
  }

  @Value("c://upload/recruitImg")
  private String uploadPath;
  @Transactional
  public void save(RecruitWriteFormDto writeFormDto, String userEmail){
    Member writer = memberRepository.findByEmail(userEmail)
            .orElseThrow(IllegalStateException::new);

    MultipartFile imgFile = writeFormDto.getImgFile();


    Recruit recruit = createRecruit(writeFormDto, writer);
    Applying applying = createWriterApplying(writer, recruit);

    recruitRepository.save(recruit);
    applyingRepository.save(applying);

    try{
        if(imgFile != null){
          upload(imgFile, "/recruit",recruit);
         }
    }catch(IOException e){
    }

  }

  @Value("${cloud.aws.s3.bucket}")
  private String bucket;
  // MultipartFile을 전달받아 File로 전환한 후 upload로 전달
  public void upload(MultipartFile imgFile, String dirName,Recruit recruit) throws IOException {
    String uploadImageUrl;

    if(recruit.getImgName() !=null ){
      String originalname = recruit.getImgName();
      amazonS3Client.deleteObject(new DeleteObjectRequest(bucket+dirName,originalname));
    }


    String fileName = createFileName(imgFile.getOriginalFilename());
    ObjectMetadata objectMetadata = new ObjectMetadata();
    objectMetadata.setContentLength(imgFile.getSize());
    objectMetadata.setContentType(imgFile.getContentType());

    try (InputStream inputStream = imgFile.getInputStream()) {
      amazonS3Client.putObject(new PutObjectRequest(bucket + dirName, fileName, inputStream, objectMetadata)
              .withCannedAcl(CannedAccessControlList.PublicRead));
      uploadImageUrl = amazonS3Client.getUrl(bucket + dirName, fileName).toString(); //업로드 후 url 반환
    } catch (IOException e) {
      throw new IllegalArgumentException("이미지 업로드 에러");
    }
    recruit.setImgName(fileName);
    recruit.setImgPath(uploadImageUrl);
  }

  // 이미지파일명 중복 방지
  private String createFileName(String fileName) {
    return UUID.randomUUID().toString().concat(fileName);
  }



  private Applying createWriterApplying(Member writer, Recruit recruit) {
    return Applying.builder()
            .id(new ApplyingId(writer.getId(), recruit.getId()))
            .isOk(true)
            .applier(writer)
            .recruit(recruit)
            .build();

  }

  private Recruit createRecruit(RecruitWriteFormDto writeFormDto, Member writer){
    return Recruit.builder()
        .title(writeFormDto.getTitle())
        .city(writeFormDto.getCity())
        .content(writeFormDto.getContent())
        .perNum(writeFormDto.getPerNum())
        .startdate(LocalDate.parse(writeFormDto.getStartdate()))
        .enddate(LocalDate.parse(writeFormDto.getEnddate()))
        .state(State.RECRUITING)
        .recruitWriter(writer)
        .build();
  }

  public void Applying(String email, Long bno){
    Member applier = memberRepository.findByEmail(email).orElseThrow();
    Recruit recruit = recruitRepository.findById(bno).orElseThrow();

     Applying applying = Applying.builder()
             .id(new ApplyingId())
             .isOk(false)
             .applier(applier)
             .recruit(recruit)
             .build();
     applyingRepository.save(applying);
  }

/*  public List<Recruit> findAll(){
    return recruitRepository.findAll();
  }*/

  public List<Recruit> getLatestRecruits() {
    return recruitRepository.findTop10ByOrderByModDateDesc();
  }

}
