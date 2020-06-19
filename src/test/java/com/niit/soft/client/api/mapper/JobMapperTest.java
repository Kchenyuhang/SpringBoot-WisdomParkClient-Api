package com.niit.soft.client.api.mapper;

import com.niit.soft.client.api.domain.dto.JobPageDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


@SpringBootTest
class JobMapperTest {

    @Resource
    private JobMapper jobMapper;

    @Test
    void findJob() {

        System.out.println(jobMapper.findJob(1L));

    }

    @Test
    void jobList() {
        JobPageDto jobPageDto = JobPageDto.builder()
                .field("max")
                .currentPage(1)
                .pageSize(5)
                .build();
        jobMapper.jobList(jobPageDto).forEach(System.out::println);
    }


    @Test
    void jobListByType() {
        JobPageDto jobPageDto = JobPageDto.builder()
                .field("3")
                .currentPage(1)
                .pageSize(2)
                .build();
        jobMapper.jobListByType(jobPageDto).forEach(System.out::println);
    }
}