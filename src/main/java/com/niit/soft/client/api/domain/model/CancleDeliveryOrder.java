package com.niit.soft.client.api.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author wl
 * @ClassNameCancleDeliveryOrder
 * @Description 取消跑腿记录表
 * @Date 2020/6/10
 * @Version 1.0
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "canle_delivery_order")
public class CancleDeliveryOrder {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 订单编号
     */
    @Column(name = "oder_id", nullable = false)
    private Long oderId;
    /**
     * 取消时间
     */
    @Column(name = "cancle_time", nullable = false)
    private LocalDateTime cancleTime;
    /**
     * 创建时间
     */
    //@JsonIgnore
    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    @JsonIgnore
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime gmtModified;

    /**
     * 删除标志（0 逻辑删除， 1 未删除）
     */
//    @JsonIgnore
    @Column(nullable = false, length = 4)
    private Boolean isDeleted;

}
