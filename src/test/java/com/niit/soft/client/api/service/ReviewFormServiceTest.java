package com.niit.soft.client.api.service;

import com.niit.soft.client.api.errends.domain.dto.ReviewFormDto;
import com.niit.soft.client.api.errends.service.ReviewFormService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ReviewFormServiceTest {
@Resource
private ReviewFormService reviewFormService;
    @Test
    void insertReview() {
        ReviewFormDto reviewFormDto =ReviewFormDto.builder()
                .idCardBack( "https://niit-soft.oss-cn-hangzhou.aliyuncs.com/UserAvatar/7ead2dc5-6e64-4b01-befb-f53d0c3270b0.jpg")
                .idCardFront( "https://niit-soft.oss-cn-hangzhou.aliyuncs.com/UserAvatar/7ead2dc5-6e64-4b01-befb-f53d0c3270b0.jpg")
                .remark(null).requesterId(5L).requesterName("王林").requesterPhonenumber("18094247962")
                .build();
        reviewFormService.insertReview(reviewFormDto);


    }
}