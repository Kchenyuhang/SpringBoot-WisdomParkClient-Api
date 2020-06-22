package com.niit.soft.client.api.controller;

import com.alipay.api.AlipayApiException;
import com.niit.soft.client.api.annotation.ControllerWebLog;
import com.niit.soft.client.api.domain.dto.AlipayDto;
import com.niit.soft.client.api.service.AliPayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName AliPayController
 * @Description TODO
 * @Author 田震
 * @Date 2020/6/1
 **/
@RestController
@Api(tags = "支付宝支付接口")
public class AliPayController {
    @Resource
    private AliPayService aliPayService;

    /**
     * 支付接口
     *
     * @param alipayDto
     * @return
     * @throws AlipayApiException
     */
    @ApiOperation("支付接口")
    @PostMapping(value = "alipay/toPay")
    @ControllerWebLog(name = "AliPay", isSaved = true)
    public String AliPay(@RequestBody AlipayDto alipayDto) throws AlipayApiException {
        return aliPayService.AliPay(alipayDto);
    }

    /**
     * 异步通知
     *
     * @return
     */
    @ApiOperation("支付异步通知接口")
    @PostMapping("alipay/notify_url")
    @ControllerWebLog(name = "notifyAliPay", isSaved = true)
    public String notifyAliPay() {
        return " a li pay notify ";
    }

    /**
     * 回调接口
     *
     * @return
     */
    @ApiOperation("支付完成以后的回调接口")
    @PostMapping("alipay/return_url")
    @ControllerWebLog(name = "returnAliPay", isSaved = true)
    public String returnAliPay() {
        return " a li pay return ";
    }
}