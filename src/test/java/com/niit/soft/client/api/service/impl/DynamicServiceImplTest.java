package com.niit.soft.client.api.service.impl;

import com.niit.soft.client.api.service.schoolmate.DynamicService;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@SpringBootTest
//@RunWith(SpringJUnit4ClassRunner.class)
class DynamicServiceImplTest {

    @Resource
    private DynamicService dynamicService;

    @Test
    void findDynamicPhotoById() {
        System.out.println(dynamicService.findDynamicPhotoById("1271709711276052480"));
    }
}