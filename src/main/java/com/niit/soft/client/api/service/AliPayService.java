package com.niit.soft.client.api.service;

import com.alipay.api.AlipayApiException;
import com.niit.soft.client.api.domain.dto.AlipayDto;

/**
 * @ClassName AliPayService
 * @Description TODO
 * @Author 田震
 * @Date 2020/6/1
 **/
public interface AliPayService {

    String AliPay(AlipayDto alipayDto) throws AlipayApiException;

}