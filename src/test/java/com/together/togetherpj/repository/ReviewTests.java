package com.together.togetherpj.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class ReviewTests {

    @Autowired
    ReviewRepository reviewRepository;

    @Test
    public void getreview() {
        reviewRepository.getMyReview(2L).forEach(c -> {
            log.info("+++++++" + c.getCity());
            log.info(c.getEnddate());
            log.info(c.getTitle());
            log.info(c.getComment());
            log.info(c.getReviewer());
        });
    }
}
