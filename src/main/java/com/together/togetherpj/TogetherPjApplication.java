package com.together.togetherpj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//commit test
@EnableJpaAuditing
@SpringBootApplication
public class TogetherPjApplication {

    public static void main(String[] args) {
        SpringApplication.run(TogetherPjApplication.class, args);
    }

}
