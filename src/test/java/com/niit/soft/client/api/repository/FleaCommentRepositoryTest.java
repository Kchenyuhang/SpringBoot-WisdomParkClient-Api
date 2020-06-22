package com.niit.soft.client.api.repository;

import com.niit.soft.client.api.domain.dto.FleaRewardDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class FleaCommentRepositoryTest {
    @Autowired
    private FleaCommentRepository commentRepository;
    @Test
    void selectByRewardId() {
        FleaRewardDto fleaRewardDto = FleaRewardDto.builder()
                .pkRewardId(5L)
                .build();
        System.out.println(commentRepository.selectByRewardId(fleaRewardDto));
    }
}