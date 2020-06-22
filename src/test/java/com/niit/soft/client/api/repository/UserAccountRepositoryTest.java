package com.niit.soft.client.api.repository;

import com.niit.soft.client.api.domain.dto.UpdateUserAccountDto;
import com.niit.soft.client.api.domain.model.UserAccount;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/6/13
 */
@SpringBootTest
@Slf4j
class UserAccountRepositoryTest {
    @Resource
    private UserAccountRepository userAccountRepository;

    @Test
    void findUserByJobNumber() {
        System.out.println(userAccountRepository.findUserByJobNumber("1802343124"));
    }

    @Test
    void updateUserAccount() {
        UpdateUserAccountDto updateUserAccountDto = UpdateUserAccountDto.builder()
                .pkUserAccountId("a6b8cdab-cc37-42be-aa58-36789c0703e6")
                .address("江苏省-南京市-栖霞区")
                .gender("男")
                .avatar("https://niit-student.oss-cn-beijing.aliyuncs.com/markdown/20200601182918.png")
                .nickname("TYX").build();
        userAccountRepository.updateUserAccount(updateUserAccountDto);
    }

    @Test
    public void getAll() {
        System.out.println(userAccountRepository.findAll());
    }
}