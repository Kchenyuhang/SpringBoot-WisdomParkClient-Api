package com.niit.soft.client.api.repository;

import com.niit.soft.client.api.domain.dto.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class FleaCollectionRepositoryTest {
    @Resource
    private FleaCollectionRepository collectionRepository;
    @Test
    void getCollection() {
        FleaUserIdDto userDto = FleaUserIdDto.
                builder()
                .pkFleaUserId(3l)
                .build();
        System.out.println(collectionRepository.getCollection(userDto));
    }
    @Test
    void delete(){
        CancelCollectionDto collectionDto = CancelCollectionDto.builder()
                .userId(8l)
                .goodsId(15l)
                .build();
        System.out.println(collectionRepository.logicalDel(collectionDto));
    }

    @Test
    void judgeCollection() {
        JudgeCollectionDto judgeCollectionDto = new JudgeCollectionDto();
        judgeCollectionDto.setGoodsId(38L);
        judgeCollectionDto.setUserId(8L);
        System.out.println(collectionRepository.judgeCollection(judgeCollectionDto).size());
    }
}