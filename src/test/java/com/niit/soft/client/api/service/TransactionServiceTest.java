package com.niit.soft.client.api.service;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.TransactionDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TransactionServiceTest {
@Resource
private TransactionService transactionService;


    @Test
    void insertTransaction() {

        TransactionDto transactionDto =TransactionDto.builder().orderId(53965137417474049L).errandsId(1L).build();
        ResponseResult responseResult = transactionService.insertTransaction(transactionDto);
        System.out.println(responseResult);
    }

    @Test
    void finshOrder() {
        ResponseResult responseResult = transactionService.finshOrder(53965137417474049L);
        System.out.println(responseResult);
    }

    @Test
    void getGoods() {
        TransactionDto transactionDto =TransactionDto.builder().orderId(53965137417474049L).build();
        ResponseResult responseResult =transactionService.getGoods(transactionDto);
        System.out.println(responseResult);
    }
}