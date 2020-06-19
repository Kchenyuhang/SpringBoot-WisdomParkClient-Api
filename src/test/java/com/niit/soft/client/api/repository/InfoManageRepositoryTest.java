package com.niit.soft.client.api.repository;

import com.niit.soft.client.api.domain.model.InfoManage;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InfoManageRepositoryTest {

    @Resource
    private InfoManageRepository infoManageRepository;

    @Test
    void getInfoManageByPkInfoManageId() {
        InfoManage infoManage = infoManageRepository.getInfoManageByPkInfoManageId((long) 1);
        System.out.println(infoManage);
    }
}