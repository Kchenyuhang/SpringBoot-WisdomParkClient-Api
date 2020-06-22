package com.niit.soft.client.api.service;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.DeliveryOrderDto;
import com.niit.soft.client.api.domain.dto.FinshOrderDto;

import java.sql.SQLException;
import java.util.Map;

/**
 * @author wl
 * @ClassNameDeliveryOrederService
 * @Description 跑腿订单模块
 * @Date 2020/6/9
 * @Version 1.0
 */
public interface DeliveryOrederService {
    /**
     * 新增订单
     *
     * @return
     * @throws SQLException
     */

    ResponseResult insertOrder(DeliveryOrderDto deliveryOrderDto);

    /**
     * 取消订单
     *
     * @return
     */
    ResponseResult cancleOrder(Long id);


    /**
     * 查询个人发布已经完成订单 或者取消订单
     */
    Map<String, Object> selectFinshOrder(FinshOrderDto finshOrderDto);

    /**
     * 逻辑删除订单
     */
    ResponseResult deleteOrder(Long id);
}
