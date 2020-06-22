package com.niit.soft.client.api.service;

import com.niit.soft.client.api.domain.model.FleaReward;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/6/22
 */
@SpringBootTest
@Slf4j
class FleaRewardServiceTest {
    @Resource
    private FleaRewardService fleaRewardService;

    @Test
    void findById() {
        log.info(String.valueOf(fleaRewardService.findById(1L)));
    }
}