package com.niit.soft.client.api.service;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.ReviewFormDto;

/**
 * @author wl
 * @ClassNameReviewFormService
 * @Description 申请成为跑腿申请表
 * @Date 2020/6/12
 * @Version 1.0
 */
public interface ReviewFormService {

    /**
     * 申请成为跑腿
     *
     * @return
     */
    ResponseResult insertReview(ReviewFormDto reviewFormDto);
}
