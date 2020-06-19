package com.niit.soft.client.api.controller;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.ReviewFormDto;
import com.niit.soft.client.api.domain.dto.TransactionDto;
import com.niit.soft.client.api.service.ReviewFormService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wl
 * @ClassNameReviewFromController
 * @Description TODO
 * @Date 2020/6/12
 * @Version 1.0
 */
@Slf4j
@RestController
@Api(tags = "跑腿接口")
@RequestMapping("/reviewfrom")
public class ReviewFromController {
    @Resource
    private ReviewFormService reviewFormService;

    @ApiOperation(value = "申请成为跑腿 ", notes = "请求参数为review dto")
    @PostMapping("/application")
    public ResponseResult insertDeliveryOrder(@RequestBody ReviewFormDto reviewFormDto) {
        return reviewFormService.insertReview(reviewFormDto);
    }
}
