package com.niit.soft.client.api.repository;

import com.jhlabs.math.VLNoise;
import com.niit.soft.client.api.domain.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Tao
 * @version 1.0
 * @ClassName SysUserAccountRepository
 * @Description TODO
 * @date 2020-05-26 9:55
 **/
public interface UserAccountRepository extends JpaRepository<UserAccount, String> {
    /**
     * 根据id查询账户信息
     *
     * @param id
     * @return
     */
    UserAccount findSysUserAccountByPkUserAccountId(String id);


    /**
     * 根据部分用户信息查询用户id
     *
     * @param userAccount, password
     * @return
     */
    @Query(value = "select pk_user_account_id from user_account as u where u.job_number=?1 and u.password=?2 or u.user_account=?1 and u.password=?2 or u.phone_number=?1 and u.password=?2", nativeQuery = true)
    Long findIdByLoginDto(String userAccount, String password);

    /**
     * 根据手机号码查询用户数据
     *
     * @param cardNumber String
     * @return Optional<UserAccount>
     */
    @Query(value = "select * from user_account as ua where ua.card_number=?1 ", nativeQuery = true)
    Optional<UserAccount> findByCardNumber(String cardNumber);

    /**
     * 根据学号（工号）查询
     *
     * @param jobNumber String
     * @return UserAccount
     */
    @Query(value = "select * from user_account as ua where ua.job_number = ?1 ", nativeQuery = true)
    UserAccount findUserByJobNumber(String jobNumber);

    /**
     * 根据部分用户信息查询用户
     *
     * @param info
     * @return
     */
    @Query(value = "select * from user_account as u where u.job_number=?1 or u.user_account=?1 or u.phone_number=?1 or u.pk_user_account_id=?1", nativeQuery = true)
    UserAccount findUserAccountByInfo(String info);

    @Transactional
    @Modifying
    @Query(value = "update user_account as u set u.password = ?2 where u.job_number=?1 or u.user_account=?1 or u" +
            ".phone_number=?1", nativeQuery = true)
    int updatePasswordByUserAccount(String userAccount, String password);

    /**
     * 根据教工号查询教师真实姓名
     *
     * @param jobNumber
     * @return
     */
    @Query(value = "select user_name from first_smart_campus.user_account as u where u.job_number=?1", nativeQuery = true)
    String findUserNameByUserJobNumber(String jobNumber);


}
