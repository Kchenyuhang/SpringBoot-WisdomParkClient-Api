package com.niit.soft.client.api.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName LoginAccountServiceTest.java
 * @Description TODO
 * @createTime 2020年06月19日 13:38:00
 */
@SpringBootTest
class LoginAccountServiceTest {

    @Resource
    private LoginAccountService loginAccountService;
    @Test
    void deletedByJobNumber() {
        loginAccountService.deletedByJobNumber("1802343210");
    }
}