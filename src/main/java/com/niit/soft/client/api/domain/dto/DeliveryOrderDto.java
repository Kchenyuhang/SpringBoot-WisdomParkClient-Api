package com.niit.soft.client.api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author wl
 * @ClassNamedeliveryOrderDto
 * @Description 跑腿订单创建
 * @Date 2020/6/9
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeliveryOrderDto {
    /**
     * 订单发起人
     */
    private Long founderId;
    /**
     * 初始位置
     */
    private String originAddress;
    /**
     * 出发地纬度
     */
    private String olongitude;
    /**
     * 出发地经度
     */
    private String odimension;
    /**
     * 目的地
     */
    private String destination;
    /**
     * 目的地经度
     */
    private String dlongitude;
    /**
     * 目的地维度
     */
    private String ddimension;

    /**
     * 送出时间（立即送出 或者指定时间）
     */
    private String deliveryTime;
    /**
     * 订单金额（起步价格6）
     */
    private BigDecimal amount;
    /**
     * 收货人名字
     */
    private String receiverName;
    /**
     * 收货人手机号
     */
    private String receiverPhoneNumber;
    /**
     * 备注
     */
    private String remark;

    /**
     * 商品信息一类
     */
    private String type;
    /**
     * 物品价格范围
     */
    private String priceRang;
}
