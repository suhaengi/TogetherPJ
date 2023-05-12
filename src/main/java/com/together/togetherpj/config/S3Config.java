package com.together.togetherpj.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {
    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Value("${cloud.aws.s3.endpoint}")
    private String endPoint;

    @Bean
    public AmazonS3Client amazonS3Client(){
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        return (AmazonS3Client) AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, region))
                .build();
    }
}
//naverCloud의 Object Storage를 사용할려면 endpoint를 네이버 클라우드로 둬야 사용 가능..
//aws s3 디펜던시에서 제공해주는 amazonS3Client라는 빈을 주입받아 그 빈을 통해 s3 저장소에 접근 후 업로드..
// s3 접근을 위한 IAM의 secret, access 키, 계정 region, endpoint를 통해  build 해줘야함

