package com.niit.soft.client.api.service;

import com.niit.soft.client.api.domain.dto.JobPageDto;
import com.niit.soft.client.api.domain.model.Job;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JobServiceTest {

    @Resource
    private JobService jobService;

    private Random random = new Random();
    @Test
    void find() {
        System.out.println(jobService.findById(1L));
    }

    @Test
    void add(){
        List<Job> jobs = new ArrayList<>();
        for (int i=1; i<100; i++){
            int min = random.nextInt(6) + 4;
            Job job = Job.builder()
                    .name("测试职位" + i)
                    .description("1、负责集团数据业务线指标产品建设及维护；\n" +
                            "2、跨部门协调和沟通，与研发团队、业务部门紧密合作，跨团队、跨部门协调沟通，跟进项目研发过程和目标达成，保证产品交付阶段的进度和质量；\n" +
                            "3、与业务线紧密合作，关注行业发展趋势，结合内外部环境，持续更新迭代具体业务线数据及指标建设，为业务提供强有力的数据支撑。")
                    .boss("name")
                    .bossPhone("15851963280")
                    .bossAvatar("https://img.bosszhipin.com/boss/avatar/avatar_16.png1")
                    .companyId((long) random.nextInt(5)+1)
                    .workplace("南京")
                    .workingTime("上午9:00-下午6:00")
                    .min(min)
                    .max(min+1)
                    .experience("经验不限")
                    .diploma("大专")
                    .jobTypeId((long) random.nextInt(32))
                    .number(random.nextInt(3))
                    .resumes("")
                    .isDeleted(false)
                    .gmtCreate(Timestamp.valueOf(LocalDateTime.now()))
                    .gmtModified(Timestamp.valueOf(LocalDateTime.now()))
                    .build();
            jobs.add(job);
        }
        jobService.saveBatch(jobs);
    }

    @Test
    void findById() {
        System.out.println(jobService.findById(1L));
    }

    @Test
    void findByType() {
        JobPageDto jobPageDto = JobPageDto.builder()
                .field("3")
                .currentPage(1)
                .pageSize(2)
                .build();
        jobService.findByType(jobPageDto).forEach(System.out::println);
    }
}