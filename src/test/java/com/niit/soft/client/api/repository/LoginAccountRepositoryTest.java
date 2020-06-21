package com.niit.soft.client.api.repository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName LoginAccountRepositoryTest.java
 * @Description TODO
 * @createTime 2020年06月18日 11:02:00
 */
@SpringBootTest
class LoginAccountRepositoryTest {

    @Resource
    private LoginAccountRepository loginAccountRepository;
    @Test
    void getLoginAccountByQqOpenIdEquals() {
//        System.out.println(loginAccountRepository.getLoginAccountByQqOpenIdEquals("16586e11c8bc44b9a8787f4a5e5cb262"));
//        System.out.println(loginAccountRepository.getLoginAccountByJobNumberEquals("1802343127"));
//          System.out.println(loginAccountRepository.deleteByJobNumberEquals("1802343210"));
          System.out.println(loginAccountRepository.existsLoginAccountByJobNumberEquals("1802343210"));
              System.out.println(loginAccountRepository.existsLoginAccountByJobNumberEquals("1802343127"));
    }
}