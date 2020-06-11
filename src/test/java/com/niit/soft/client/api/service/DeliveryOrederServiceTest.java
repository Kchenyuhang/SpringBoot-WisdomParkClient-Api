package com.niit.soft.client.api.service;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.DeliveryOrderDto;
import com.niit.soft.client.api.domain.dto.FinshOrderDto;
import com.niit.soft.client.api.domain.model.DeliveryOrder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@SpringBootTest
class DeliveryOrederServiceTest {
    @Resource
    private DeliveryOrederService deliveryOrederService;

    /**
     * 新增数据
     */
    @Test
    void insertOrder() {
        for (int i = 0; i < 100; i++) {
            DeliveryOrderDto deliveryOrderDto = DeliveryOrderDto.builder()
                    .deliveryTime(LocalDateTime.now())
                    .amount(BigDecimal.valueOf(25)).dDimension("33.3").destination("南工院北门").dLongitude("78.2")
                    .founderId(5L).oDimension("99.6").oLongitude("66.5").originAddress("南工院西门")
                    .priceRang("1-200").receiverName("王妃").receiverPhoneNumber("18094247965").remark("").type("食物")
                    .build();

            ResponseResult responseResult = deliveryOrederService.insertOrder(deliveryOrderDto);

            if (i == 99) {
                break;
            }
            i++;
        }
    }

    @Test
    void cancleOrder() {
        ResponseResult responseResult = deliveryOrederService.cancleOrder(53868917554548737L);
        System.out.println(responseResult);
    }

    @Test
    void selectFinshOrder() {
        //查询所有未取消订单
        FinshOrderDto finshOrderDto = FinshOrderDto.builder().founderId(5L).isCancle(0).num(1).size(5).build();
        Map<String, Object> map = deliveryOrederService.selectFinshOrder(finshOrderDto);
        System.out.println(map);
    }
}