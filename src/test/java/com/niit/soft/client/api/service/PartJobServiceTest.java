
package com.niit.soft.client.api.service;

import com.niit.soft.client.api.domain.dto.JobPageDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class PartJobServiceTest {

    @Resource
    private PartJobService partJobService;

    @Test
    void findByPage() {
        JobPageDto jobPageDto = JobPageDto.builder()
                .field("pay")
                .currentPage(1)
                .pageSize(10)
                .build();
        System.out.println(partJobService.findByPage(jobPageDto));
    }

    @Test
    void insertJob() {

    }

    @Test
    void findById() {
        System.out.println(partJobService.findById(1L));
    }

}