package com.niit.soft.client.api.repository;

import com.niit.soft.client.api.domain.model.CancleDeliveryOrder;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wl
 * @ClassNameCancleDeliveryOderRepository
 * @Description 取消订单模块
 * @Date 2020/6/10
 * @Version 1.0
 */
public interface CancleDeliveryOderRepository extends JpaRepository<CancleDeliveryOrder, Long> {
}
