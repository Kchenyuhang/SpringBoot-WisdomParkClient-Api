package com.niit.soft.client.api.controller;

import com.alipay.api.domain.RequestExtShopItem;
import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.DeliveryOrderDto;
import com.niit.soft.client.api.domain.dto.TransactionDto;
import com.niit.soft.client.api.service.DeliveryOrederService;
import com.niit.soft.client.api.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author wl
 * @ClassNameTransactionController
 * @Description TODO
 * @Date 2020/6/9
 * @Version 1.0
 */
@Slf4j
@RestController
@Api(tags = "跑腿接口")
@RequestMapping("/transaction")
public class TransactionController {
    @Resource
    private TransactionService transactionService;

    @ApiOperation(value = "抢到订单 ", notes = "请求参数为订单dto")
    @PostMapping("/order")
    public ResponseResult insertDeliveryOrder(@RequestBody TransactionDto transactionDto) {
        return transactionService.insertTransaction(transactionDto);
    }


    @ApiOperation(value = "取货", notes = "请求参数是订单dto")
    @PostMapping("/getgoods")
    public ResponseResult getGoods(@RequestBody TransactionDto transactionDto) {
        return transactionService.getGoods(transactionDto);
    }

    @ApiOperation(value = "完成订单", notes = "请求参数是订单id")
    @PostMapping("/finshOrder")
    public ResponseResult finshOrder(@RequestBody TransactionDto transactionDto) {
        return transactionService.finshOrder(transactionDto.getOrderId());
    }

}
