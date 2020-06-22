package com.niit.soft.client.api.service;

import com.niit.soft.client.api.domain.dto.JudgeCollectionDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/6/18
 */
@SpringBootTest
@Slf4j
class FleaCollectionServiceTest {
    @Resource
    private FleaCollectionService fleaCollectionService;

    @Test
    void judgeCollection() {
        JudgeCollectionDto judgeCollectionDto = new JudgeCollectionDto();
        judgeCollectionDto.setUserId(8L);
        judgeCollectionDto.setGoodsId(38L);
        log.info(String.valueOf(fleaCollectionService.judgeCollection(judgeCollectionDto)));
    }
}