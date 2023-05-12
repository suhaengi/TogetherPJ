package com.together.togetherpj.service;

import com.together.togetherpj.domain.Member;
import com.together.togetherpj.dto.ProfileDto;
import com.together.togetherpj.dto.PwForm;
import com.together.togetherpj.repository.MemberRepository;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;

@Transactional
@Service
@RequiredArgsConstructor
@Component
@Slf4j
public class ProfileService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    //mypage불러올때
    public ProfileDto readOne(String email) throws IOException {

        Member member = memberRepository.findByEmail(email).orElseThrow();

        ProfileDto profileDto = ProfileDto.builder()
                .email(email)
                .nickname(member.getNickname())
                .intro(member.getIntro())
                .gender(member.getGender())
                .birth(member.getBirth())
                .regDate(LocalDate.from(member.getRegDate()))
                .like(member.getLike())
                .profileImgPath(member.getProfileImgPath())
                .profileImgName(member.getProfileImgName())
                .build();

        return profileDto;
    }

    //프로필편집 페이지 불러올 때
    public ProfileDto readForEdit(String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow();
        ProfileDto dto = ProfileDto.builder()
                .name(member.getName())
                .gender(member.getGender())
                .birth(member.getBirth())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .intro(member.getIntro())
                .phone(member.getPhone())
                .build();
        return dto;
    }

    //프로필 편집할때
    public void change(Authentication authentication, ProfileDto profileDto) {
        String email = authentication.getName();
        Member member = memberRepository.findByEmail(email).orElseThrow();
        member.setNickname(profileDto.getNickname());
        member.setIntro(profileDto.getIntro());
        member.setPhone(profileDto.getPhone());
    }

    //비밀번호 변경
    public void PWchange(Authentication authentication, PwForm pwForm) {
        String email = authentication.getName();
        Member member = memberRepository.findByEmail(email).orElseThrow();
        member.setPassword(passwordEncoder.encode(pwForm.getPassword()));
    }

/*    //이미지 업로드할 때
    @Value("c://upload")
    private String uploadPath;
    public void saveImg(Authentication authentication, MultipartFile imgFile) throws IOException {

        String email = authentication.getName();
        Member member = memberRepository.findByEmail(email).orElseThrow();
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + imgFile.getOriginalFilename();
        File profileImg=  new File(uploadPath,fileName);
        imgFile.transferTo(profileImg);
        member.setProfileImgName(fileName);
        member.setProfileImgPath(uploadPath+"/"+fileName);
    }

    //이미지 저장
    public void saveImg(Authentication authentication, MultipartFile multipartFile,String dirName) throws IOException {
        String email = authentication.getName();
        Member member = memberRepository.findByEmail(email).orElseThrow();

        String uploadImageUrl;

        String fileName = createFileName(multipartFile.getOriginalFilename());
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(multipartFile.getSize());
        objectMetadata.setContentType(multipartFile.getContentType());

        try(InputStream inputStream = multipartFile.getInputStream()) {
            amazonS3Client.putObject(new PutObjectRequest(bucket+dirName, fileName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));

            uploadImageUrl = amazonS3Client.getUrl(bucket+dirName, fileName).toString(); //업로드 후 url 반환
        } catch(IOException e) {
            throw new IllegalArgumentException("이미지 업로드 에러");
        }

        member.setProfileImgName(fileName);
        member.setProfileImgPath(uploadImageUrl+"/"+fileName);
    }
     */
}