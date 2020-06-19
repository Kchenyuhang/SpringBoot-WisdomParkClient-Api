package com.niit.soft.client.api.mapper;

import com.niit.soft.client.api.domain.vo.AddressBookVo;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Tao
 */
public interface UserAccountMapper {
    /**
     * 模糊查询
     *
     * @param keyword
     * @return
     * @throws SQLException
     */
    @Select({"<script>",
            "SELECT pk_user_account_id,user_name,phone_number,avatar FROM user_account ",
            "WHERE 1=1 ",
            "<when test='keywords!=null'> ",
            "AND user_name LIKE CONCAT('%',#{keywords},'%') ",
            "OR phone_number LIKE CONCAT('%',#{keywords},'%') ",
            "</when> ",
            "</script>"})
    List<AddressBookVo> findUserAccountLike(String keyword) throws SQLException;
}
