package com.niit.soft.client.api.controller;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.DeliveryOrderDto;
import com.niit.soft.client.api.domain.dto.FinshOrderDto;
import com.niit.soft.client.api.domain.dto.TransactionDto;
import com.niit.soft.client.api.service.DeliveryOrederService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author wl
 * @ClassNameDeliveryOrderController
 * @Description TODO
 * @Date 2020/6/9
 * @Version 1.0
 */
@Slf4j
@RestController
@Api(tags = "跑腿接口")
@RequestMapping("/errands")
public class DeliveryOrderController {
    @Resource
    private DeliveryOrederService deliveryOrederService;

    @ApiOperation(value = "客户新增跑腿订单 ", notes = "请求参数为订单dto")
    @PostMapping("/order")
    public ResponseResult insertDeliveryOrder(@RequestBody DeliveryOrderDto deliveryOrderDto) {
        log.info(String.valueOf(deliveryOrderDto));
        return deliveryOrederService.insertOrder(deliveryOrderDto);
    }


    @ApiOperation(value = "取消订单 、", notes = "请求参数为订单id")
    @PostMapping("/cancle/order")
    public ResponseResult cancleOrder(@RequestBody TransactionDto transactionDto) {

        return deliveryOrederService.cancleOrder(transactionDto.getOrderId());
    }


    @ApiOperation(value = "查询对应订单 已经取消 已经完成", notes = "请求参数为Dto 参数为 用户id 以及不同状态 页数 每页个数 ")
    @PostMapping("/differentOrder")
    public Map<String, Object> findDifferentOrder(@RequestBody FinshOrderDto finshOrderDto) {
        return deliveryOrederService.selectFinshOrder(finshOrderDto);
    }

    @ApiOperation(value = "删除非请求订单", notes = "请求参数为Dto 参数为 订单id  ")
    @PostMapping("/delete/order")
    public ResponseResult deleteOrder(@RequestBody TransactionDto transactionDto) {
        return deliveryOrederService.cancleOrder(transactionDto.getOrderId());
    }
}
