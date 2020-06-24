package com.niit.soft.client.api.service;

import com.niit.soft.client.api.domain.dto.FinshOrderDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DeliveryOrederServiceTest {
    @Resource
    private DeliveryOrederService deliveryOrederService;
    @Test
    void selectFinshOrder() {
        FinshOrderDto finshOrderDto = FinshOrderDto.builder()
                .founderId(1802333101l)
                .status(0)
                .num(1)
                .size(1)
                .build();
        System.out.println(deliveryOrederService.selectFinshOrder(finshOrderDto));
    }
}