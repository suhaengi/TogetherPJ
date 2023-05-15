package com.together.togetherpj.img;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
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
import java.net.URLDecoder;
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
    public void upload(MultipartFile imgFile, String dirName, Authentication authentication) throws IOException {
        String email = authentication.getName();
        Member member = memberRepository.findByEmail(email).orElseThrow();
        String uploadImageUrl;

        //만약 프로필이미지가 이미 존재한다면 존재하는 것을 삭제하고 등록
        // (서버 저장소에 불필요한 용량차지 방지)
        if(member.getProfileImgPath() !=null ){
            String originalname = member.getProfileImgName();
            amazonS3Client.deleteObject(new DeleteObjectRequest(bucket+dirName,originalname));
        }

        //파일 이름이 중복되지 않도록 UUID이용
        String fileName = UUID.randomUUID().toString().concat(imgFile.getOriginalFilename());

        //InputStream을 통해 Byte만이 전달 되기 때문에,해당 파일에 대한 정보가 없음.
        //따라서 ObjectMetadata에 파일에 대한 정보를 추가하여 매개변수로 같이 전달
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(imgFile.getSize());
        objectMetadata.setContentType(imgFile.getContentType());

        //버킷이름, 폴더명, 파일명, InputStream, objectMetadata 정보를 이용하여 서버저장소에 파일 저장
        try (InputStream inputStream = imgFile.getInputStream()) {
            amazonS3Client.putObject(new PutObjectRequest(bucket + dirName, fileName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
            uploadImageUrl = amazonS3Client.getUrl(bucket + dirName, fileName).toString(); //업로드 후 url 반환
        } catch (IOException e) {
            throw new IllegalArgumentException("업로드 에러");
        }
        
        //저장 후 member에 파일명과 경로 저장
        member.setProfileImgName(fileName);
        member.setProfileImgPath(uploadImageUrl);
    }

    // 이미지파일명 중복 방지
    private String createFileName(String fileName) {
        return UUID.randomUUID().toString().concat(fileName);
    }
}
