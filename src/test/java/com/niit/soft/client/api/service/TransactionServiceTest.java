package com.niit.soft.client.api.service;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.errends.domain.dto.TransactionDto;
import com.niit.soft.client.api.errends.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class TransactionServiceTest {
@Resource
private TransactionService transactionService;


    @Test
    void insertTransaction() {
        TransactionDto transactionDto =TransactionDto.builder().orderId("53965137417474049").errandsId("1").build();
        ResponseResult responseResult = transactionService.insertTransaction(transactionDto);
        System.out.println(responseResult);
    }

    @Test
    void finshOrder() {
        ResponseResult responseResult = transactionService.finshOrder("53965137417474049");
        System.out.println(responseResult);
    }

    @Test
    void getGoods() {
        TransactionDto transactionDto =TransactionDto.builder().orderId("53965137417474049").build();
        ResponseResult responseResult =transactionService.getGoods(transactionDto);
        System.out.println(responseResult);
    }
}