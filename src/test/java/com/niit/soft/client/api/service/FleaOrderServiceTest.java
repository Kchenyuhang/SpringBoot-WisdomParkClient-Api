package com.niit.soft.client.api.service;

import com.niit.soft.client.api.domain.dto.FleaOrderDto;
import com.niit.soft.client.api.domain.model.FleaOrder;
import com.niit.soft.client.api.repository.FleaGoodsRepository;
import com.niit.soft.client.api.repository.FleaOrderRepository;
import com.niit.soft.client.api.repository.FleaUserRepository;
import com.niit.soft.client.api.util.SnowFlake;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FleaOrderServiceTest {
    @Resource
    private FleaOrderRepository fleaOrderRepository;
    @Resource
    private FleaUserRepository userRepository;
    @Resource
    private FleaGoodsRepository goodsRepository;
    @Resource
    private FleaOrderService orderService;

    @Test
    void orderIncreased() {
        FleaOrderDto fleaOrderDto = FleaOrderDto.builder()
                .pkFleaOrderId("29202992")
                .fleaGoodsPkFleaGoodsId(37l)
                .fleaUserBuyerPkFleaUserId(3l)
                .fleaUserSellerPkFleaUserId(25l)
                .build();
        System.out.println(orderService.orderIncreased(fleaOrderDto));
    }
}