package com.niit.soft.client.api.service.impl;

import com.alipay.api.domain.AddressDTO;
import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.common.ResultCode;
import com.niit.soft.client.api.domain.dto.QueryDto;
import com.niit.soft.client.api.domain.vo.AddressBookVo;
import com.niit.soft.client.api.mapper.UserAccountMapper;
import com.niit.soft.client.api.repository.UserAccountRepository;
import com.niit.soft.client.api.domain.model.UserAccount;
import com.niit.soft.client.api.service.UserAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/26 10:22
 * @Version 1.0
 **/
@Service
public class UserAccountServiceImpl implements UserAccountService {
    @Resource
    private UserAccountRepository userAccountRepository;
    @Resource
    private UserAccountMapper userAccountMapper;
    @Override
    public UserAccount findUserAccountById(String id) {
        return userAccountRepository.findUserAccountByInfo(id.toString());
    }

    @Override
    public UserAccount findUserAccountByInfo(String info) {
        return userAccountRepository.findUserAccountByInfo(info);
    }

    @Override
    public int updatePasswordByUserAccount(String userAccount, String password) {
        return userAccountRepository.updatePasswordByUserAccount(userAccount, password);
    }

    @Override
    public ResponseResult findUserAccountLike(String keyword) {
        List<AddressBookVo> addressBookVos = null;
        try {
            addressBookVos = userAccountMapper.findUserAccountLike(keyword);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ResponseResult.success(addressBookVos);
    }

    @Override
    public ResponseResult updateUserInfo(UserAccount sysUserAccount) {
        UserAccount updateSysUserAccount = userAccountRepository.findUserAccountByInfo(sysUserAccount.getPkUserAccountId().toString());
        //判断帐号是否被禁用
        if (updateSysUserAccount.getStatus()) {
            updateSysUserAccount.setAvatar(sysUserAccount.getAvatar());
            updateSysUserAccount.setNickname(sysUserAccount.getNickname());
            updateSysUserAccount.setGender(sysUserAccount.getGender());
            updateSysUserAccount.setAddress(sysUserAccount.getAddress());
            userAccountRepository.saveAndFlush(updateSysUserAccount);
            return ResponseResult.success(updateSysUserAccount);
        }
        return ResponseResult.failure(ResultCode.USER_ACCOUNT_FORBIDDEN);
    }

    @Override
    public ResponseResult updateUserPhoneNumber(UserAccount userAccount) {
        UserAccount updateSysUserAccount = userAccountRepository.findUserAccountByInfo(userAccount.getPkUserAccountId().toString());
        if (updateSysUserAccount.getStatus()) {
            updateSysUserAccount.setPhoneNumber(userAccount.getPhoneNumber());
            userAccountRepository.saveAndFlush(updateSysUserAccount);
            return ResponseResult.success(updateSysUserAccount);
        }
        return ResponseResult.failure(ResultCode.USER_ACCOUNT_FORBIDDEN);
    }

}
