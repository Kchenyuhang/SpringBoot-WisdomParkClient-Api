package com.niit.soft.client.api.service.impl;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.LoginAccountIncreased;
import com.niit.soft.client.api.domain.model.LoginAccount;
import com.niit.soft.client.api.repository.LoginAccountRepository;
import com.niit.soft.client.api.service.LoginAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName LoginAccountServiceImpl.java
 * @Description TODO
 * @createTime 2020年06月17日 22:59:00
 */
@Service
public class LoginAccountServiceImpl implements LoginAccountService {
    @Resource
    private LoginAccountRepository loginAccountRepository;

    @Override
    public ResponseResult increasedLoginAccount(LoginAccountIncreased loginAccountIncreased) {
        LoginAccount loginAccount = LoginAccount.builder()
                .jobNumber(loginAccountIncreased.getJobNumber())
                .qqOpenId(loginAccountIncreased.getQqOpenId())
                .build();
        return ResponseResult.success(loginAccountRepository.save(loginAccount));
    }
}
