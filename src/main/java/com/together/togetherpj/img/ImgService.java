package com.together.togetherpj.img;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.together.togetherpj.domain.Member;
import com.together.togetherpj.repository.MemberRepository;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Transactional
@Service
@RequiredArgsConstructor
@Component
@Slf4j
public class ImgService {

    private final MemberRepository memberRepository;
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    // MultipartFile을 전달받아 File로 전환한 후 upload로 전달
    public void upload(MultipartFile imgFile, String dirName, Authentication authentication) throws IOException {
        String email = authentication.getName();
        Member member = memberRepository.findByEmail(email).orElseThrow();

        String uploadImageUrl;

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
        member.setProfileImgName(fileName);
        member.setProfileImgPath(uploadImageUrl);
    }

    // 이미지파일명 중복 방지
    private String createFileName(String fileName) {
        return UUID.randomUUID().toString().concat(fileName);
    }
}
