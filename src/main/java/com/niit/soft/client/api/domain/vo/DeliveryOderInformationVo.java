package com.niit.soft.client.api.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.niit.soft.client.api.domain.model.Commodity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author wl
 * @ClassNameDeliveryOderInformationVo
 * @Description 订单详情
 * @Date 2020/6/10
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeliveryOderInformationVo {
    /**
     * 订单id
     */
    private Long id;
    /**
     * 订单发起人
     */
    private Long founderId;
    /**
     * 初始位置
     */
    private String originAddress;
    /**
     * 目的地
     */
    private String destination;
    /**
     * 商品详细信息
     */
    private Commodity commodity;
    /**
     * 订单创建时间
     */
    private LocalDateTime oderCreateTime;
    /**
     * 送出时间（立即送出 或者指定时间）
     */
    private LocalDateTime deliveryTime;
    /**
     * 订单取消时间
     */
    private LocalDateTime cancleTime;
    /**
     * 完成模块
     */
    //完成人名字
    private String name;
    /**
     * 完成人的手机号
     */
    private String errendsPhoneNumber;
    /**
     * 完成时间
     */
    private LocalDateTime finshTime;
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

}
