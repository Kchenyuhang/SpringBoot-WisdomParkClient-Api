package com.niit.soft.client.api.service;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.FleaSearchDto;
import com.niit.soft.client.api.domain.dto.PageDto;
import com.niit.soft.client.api.domain.dto.FleaRewardDto;
import com.niit.soft.client.api.domain.dto.SingleFieldDto;
import com.niit.soft.client.api.domain.model.FleaReward;
import org.springframework.data.domain.Page;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName FleaRewardService.java
 * @Description TODO
 * @createTime 2020年06月09日 14:05:00
 */
public interface FleaRewardService {
    Page<FleaReward> findFleaRewardByContent(FleaSearchDto fleaSearchDto);

    Page<FleaReward> findAll(PageDto pageDto);

    /**
     * 查出最新的两条悬赏数据
     *
     * @return
     */
    ResponseResult getRewardTopThree();


    /**
     * 添加悬赏
     *
     * @param fleaRewardDto
     * @return
     */
    ResponseResult save(FleaRewardDto fleaRewardDto);

    /**
     * 修改悬赏
     *
     * @param fleaRewardDto
     * @return
     */
    ResponseResult update(FleaRewardDto fleaRewardDto);

    /**
     * 删除悬赏
     *
     * @param rewardId
     * @return
     */
    ResponseResult delete(Long rewardId);
}
