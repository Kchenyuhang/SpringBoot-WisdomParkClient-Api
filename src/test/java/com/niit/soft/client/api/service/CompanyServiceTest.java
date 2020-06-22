package com.niit.soft.client.api.service;

import com.niit.soft.client.api.domain.dto.JobPageDto;
import com.niit.soft.client.api.domain.dto.PageDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CompanyServiceTest {

    @Resource
    private CompanyService companyService;

    @Test
    void findByPage() {
        JobPageDto jobPageDto = JobPageDto.builder()
                .field("workers")
                .currentPage(1)
                .pageSize(10)
                .build();
        companyService.findByPage(jobPageDto).forEach(System.out::println);

    }

    @Test
    void findById() {
        System.out.println(companyService.findById(1L));
    }
}