package com.together.togetherpj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//commit test
@EnableJpaAuditing
@SpringBootApplication
@EnableJpaAuditing  //AuditingEntityListener을 활성화 시키기 위해 설정
public class TogetherPjApplication {

    public static void main(String[] args) {
        SpringApplication.run(TogetherPjApplication.class, args);
    }

}
