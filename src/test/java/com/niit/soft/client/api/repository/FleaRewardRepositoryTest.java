package com.niit.soft.client.api.repository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class FleaRewardRepositoryTest {
    @Resource
    private FleaRewardRepository fleaRewardRepository;
    @Test
    void getTopReward() {
    }
}