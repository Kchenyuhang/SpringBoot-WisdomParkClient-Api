package com.niit.soft.client.api.controller;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.common.ResultCode;
import com.niit.soft.client.api.service.SendSmsService;
import org.apache.logging.log4j.util.StringBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/21 10:31
 * @Version 1.0
 **/
@RestController
@CrossOrigin  //跨域支持
public class SmsApiController {
    @Autowired
    private SendSmsService sendSmsService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @RequestMapping(value = "/sendCode",method = RequestMethod.POST)
    public ResponseResult code(@RequestParam("phoneNumber")String phoneNumber) {
        //调用发送方法
        System.out.println("接受的phoneNumber"+phoneNumber);
        String code = redisTemplate.opsForValue().get(phoneNumber);
        if (!StringUtils.isEmpty(code)) {
            //数据已存在
            return ResponseResult.failure(ResultCode.DATA_ALREADY_EXISTED,phoneNumber + ":" + code + "已存在，还没有过期");
        }
        //生成验证码并存储到redis中
        code = UUID.randomUUID().toString().substring(0, 4);
        HashMap<String, Object> param = new HashMap<>();
        param.put("code", code);
        boolean sms = sendSmsService.send(phoneNumber, "SMS_190277609", param);
        if (sms){
            redisTemplate.opsForValue().set(phoneNumber, code, 5, TimeUnit.SECONDS);
            return ResponseResult.success(code);
        }else {
            return ResponseResult.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }
}
