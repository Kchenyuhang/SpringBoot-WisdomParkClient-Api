package com.niit.soft.client.api.controller;

import com.niit.soft.client.api.annotation.ControllerWebLog;
import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.common.ResultCode;
import com.niit.soft.client.api.domain.dto.PageDto;
import com.niit.soft.client.api.domain.model.Dynamic;
import com.niit.soft.client.api.service.DynamicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName DynamicController
 * @Description 好友圈模块动态资讯
 * @Author xiaobinggan
 * @Date 2020/6/8 8:14 下午
 * @Version 1.0
 **/
@Slf4j
@RestController
@RequestMapping("/dynamic")
@Api(tags = "好友动态资讯接口")
public class DynamicController {
    @Resource
    private DynamicService dynamicService;

    @PostMapping("/{id}")
    @ControllerWebLog(name = "findDynamicVoById", isSaved = true)
    @ApiOperation(value = "好友圈根据id查找动态资讯", notes = "请求参数为动态id")
    ResponseResult findDynamicVoById(@PathVariable int id) {
        return ResponseResult.success(dynamicService.findDynamicVoById((long) id));
    }

    @PostMapping
    @ControllerWebLog(name = "findDynamic", isSaved = true)
    @ApiOperation(value = "查找所有动态资讯", notes = "请求参数为传递分页参数")
    ResponseResult findDynamic(@RequestBody PageDto pageDto) {
        return ResponseResult.success(dynamicService.findDynamicByPage(pageDto));
    }

    @PostMapping("/new")
    @ControllerWebLog(name = "findDynamic", isSaved = true)
    @ApiOperation(value = "查找所有动态资讯", notes = "请求参数为传递分页参数")
    ResponseResult addOne(@RequestBody Dynamic dynamic) {
        int i = dynamicService.addOne(dynamic);
        if (i == 1) {
            return ResponseResult.success();
        }
        return ResponseResult.failure(ResultCode.DATA_IS_WRONG);
    }

    @PostMapping("/comment/{id}")
    @ControllerWebLog(name = "findCommentVoById", isSaved = true)
    @ApiOperation(value = "好友圈根据评论id查找多级评论", notes = "请求参数为评论id")
    ResponseResult findCommentVoById(@PathVariable int id) {
        return ResponseResult.success(dynamicService.findCommentVoById((long) id));
    }

}