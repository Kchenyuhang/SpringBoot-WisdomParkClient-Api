package com.niit.soft.client.api.service;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.errends.domain.dto.DeliveryOrderDto;
import com.niit.soft.client.api.errends.domain.dto.FinshOrderDto;
import com.niit.soft.client.api.errends.service.DeliveryOrederService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
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
                    .deliveryTime("2020-06-01 10:12:05")
                    .amount(BigDecimal.valueOf(25)).ddimension("33.3").destination("南工院北门").dlongitude("78.2")
                    .founderId(5L).odimension("99.6").olongitude("66.5").originAddress("南工院西门")
                    .priceRang("1-200").receiverName("王妃").receiverPhoneNumber("18094247965").founderName("王林").founderPhonenumer("18094247968").remark("").type("食物")
                    .build();

            ResponseResult responseResult = deliveryOrederService.insertOrder(deliveryOrderDto);

            if (i == 99) {
                break;
            }
          
        }
    }

    @Test
    void cancleOrder() {
        ResponseResult responseResult = deliveryOrederService.cancleOrder(54253938647896065L);
        System.out.println(responseResult);
    }

    @Test
    void selectFinshOrder() {
        //查询所有未取消订单
        FinshOrderDto finshOrderDto = FinshOrderDto.builder().founderId(5L).num(1).size(5).build();
        Map<String, Object> map = deliveryOrederService.selectFinshOrder(finshOrderDto);
        System.out.println(map);
    }

    @Test
    void deleteOrder() {
        System.out.println(deliveryOrederService.deleteOrder(54253938647896065L));
    }
}