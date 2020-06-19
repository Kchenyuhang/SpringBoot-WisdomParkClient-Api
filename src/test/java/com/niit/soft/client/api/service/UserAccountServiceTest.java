package com.niit.soft.client.api.service;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.UpdateUserAccountDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserAccountServiceTest {

    @Resource
    private UserAccountService userAccountService;

    @Test
    void findUserAccountLike() {
        ResponseResult result = userAccountService.findUserAccountLike("曹");
        System.out.println(result);
    }

    @Test
    void updateUserInfo() {
        UpdateUserAccountDto updateUserAccountDto = UpdateUserAccountDto.builder()
                .pkUserAccountId("a6b8cdab-cc37-42be-aa58-36789c0703e6")
                .address("江苏省-南京市-江宁区")
                .gender("男")
                .avatar("https://niit-student.oss-cn-beijing.aliyuncs.com/markdown/20200601182918.png")
                .nickname("TYX").build();
        ResponseResult result = userAccountService.updateUserInfo(updateUserAccountDto);
        System.out.println(result);
    }
}