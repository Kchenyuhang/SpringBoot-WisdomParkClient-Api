package com.niit.soft.client.api.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 描述:
 *
 * @author：Guorc
 * @create 2020-06-09 15:15
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GoodsVo {
    private Long goodsId;
    private String goodsName;
    private Double goodsPrice;
    private String goodsDescription;
    private String goodsMarks;
    private String goodsImgUrl;
    private Date goodsCreateTime;
    private String goodsType;
    private String goodsUserName;
    private Boolean isDeleted;
}
