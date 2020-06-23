package com.niit.soft.client.api.repository;

import com.niit.soft.client.api.domain.model.ReportLoss;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReportLossRepositoryTest {

    @Resource
    private ReportLossRepository reportLossRepository;

    @Test
    void findReportLoss() {
        ReportLoss reportLoss = reportLossRepository.findReportLoss("1802333101","123456");
        System.out.println(reportLoss);
    }
}