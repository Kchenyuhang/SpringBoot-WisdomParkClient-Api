package com.niit.soft.client.api.service.impl;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.ReviewFormDto;
import com.niit.soft.client.api.domain.model.ReviewForm;
import com.niit.soft.client.api.repository.ReviewFormRepository;
import com.niit.soft.client.api.service.ReviewFormService;
import com.niit.soft.client.api.util.SnowFlake;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.xml.transform.Result;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author wl
 * @ClassNameReviewFormServiceImpl
 * @Description TODO
 * @Date 2020/6/12
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class ReviewFormServiceImpl implements ReviewFormService {
    @Resource
    private ReviewFormRepository reviewFormRepository;

    @Override
    public ResponseResult insertReview(ReviewFormDto reviewFormDto) {
        SnowFlake snowFlake = new SnowFlake(1, 3);
        long l = snowFlake.nextId();
        //新增申请记录
        ReviewForm reviewForm = ReviewForm.builder()
                .id(l).gmtCreate(Timestamp.valueOf(LocalDateTime.now())).gmtModified(Timestamp.valueOf(LocalDateTime.now())).idCardBack(reviewFormDto.getIdCardBack())
                .idCardFront(reviewFormDto.getIdCardFront()).requesterId(reviewFormDto.getRequesterId())
                .remark(reviewFormDto.getRemark()).requesterName(reviewFormDto.getRequesterName()).requesterPhonenumber(reviewFormDto.getRequesterPhonenumber()).status(0L).isDeleted(false)
                .build();
        reviewFormRepository.save(reviewForm);
        return ResponseResult.success();

    }
}
