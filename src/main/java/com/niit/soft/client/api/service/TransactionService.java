package com.niit.soft.client.api.service;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.TransactionDto;

/**
 * @author wl
 * @ClassNameTransaction
 * @Description 交易表
 * @Date 2020/6/9
 * @Version 1.0
 */
public interface TransactionService {
    /**
     * 新增交易信息 订单交易
     *
     * @param transactionDto
     * @return
     */
    ResponseResult insertTransaction(TransactionDto transactionDto);

    /**
     * 完成订单
     *
     * @param orderId
     * @return
     */
    ResponseResult finshOrder(Long orderId);

    /**
     * 取货
     *
     * @param transactionDto
     * @return
     */
    ResponseResult getGoods(TransactionDto transactionDto);
}
