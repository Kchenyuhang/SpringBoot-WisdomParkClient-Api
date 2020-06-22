package com.niit.soft.client.api.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * @author wl
 * @ClassNameReviewFormDto
 * @Description TODO
 * @Date 2020/6/12
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewFormDto {

    /**
     * 申请人id
     */

    private Long requesterId;
    /**
     * 申请人正面身份证
     */

    private String idCardFront;
    /**
     * 申请人背面身份证
     */

    private String idCardBack;
    /**
     * 申请人手机号
     */

    private String requesterPhonenumber;
    /**
     * 申请人手机号
     */

    private String requesterName;

    /**
     * 申请原因
     */

    private String remark;

}
