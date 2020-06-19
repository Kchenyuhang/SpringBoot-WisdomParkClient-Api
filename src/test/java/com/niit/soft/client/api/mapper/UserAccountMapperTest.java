package com.niit.soft.client.api.mapper;

import com.niit.soft.client.api.domain.vo.AddressBookVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class UserAccountMapperTest {

    @Resource
    private UserAccountMapper userAccountMapper;

    @Test
    void findUserAccountLike() throws SQLException {
        List<AddressBookVo> addressBookVos = userAccountMapper.findUserAccountLike("15152231");
        System.out.println(addressBookVos);
    }
}