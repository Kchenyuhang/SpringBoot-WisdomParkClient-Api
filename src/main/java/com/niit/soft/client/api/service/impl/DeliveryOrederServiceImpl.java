package com.niit.soft.client.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.common.ResultCode;
import com.niit.soft.client.api.domain.dto.DeliveryOrderDto;
import com.niit.soft.client.api.domain.dto.FinshOrderDto;
import com.niit.soft.client.api.domain.model.CancleDeliveryOrder;
import com.niit.soft.client.api.domain.model.Commodity;
import com.niit.soft.client.api.domain.model.DeliveryOrder;
import com.niit.soft.client.api.domain.model.Transaction;
import com.niit.soft.client.api.mapper.CommodityMapper;
import com.niit.soft.client.api.mapper.DeliveryOrderMapper;
import com.niit.soft.client.api.mapper.TransactionMapper;
import com.niit.soft.client.api.repository.CancleDeliveryOderRepository;
import com.niit.soft.client.api.repository.CommondityRepository;
import com.niit.soft.client.api.repository.DeliveryOderRepository;
import com.niit.soft.client.api.service.DeliveryOrederService;
import com.niit.soft.client.api.util.DateTest;
import com.niit.soft.client.api.util.SnowFlake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wl
 * @ClassNameDeliveryOrederServiceImpl
 * @Description TODO
 * @Date 2020/6/9
 * @Version 1.0
 */
@Service
@Slf4j
@Transactional(rollbackFor = RuntimeException.class)
public class DeliveryOrederServiceImpl extends ServiceImpl<DeliveryOrderMapper, DeliveryOrder> implements DeliveryOrederService {
    @Resource
    private CommodityMapper commodityMapper;
    @Resource
    private DeliveryOrderMapper deliveryOrderMapper;
    @Resource
    private TransactionMapper transactionMapper;
    @Resource
    private CommondityRepository commondityRepository;
    @Resource
    private DeliveryOderRepository deliveryOderRepository;
    @Resource
    private CancleDeliveryOderRepository cancleDeliveryOderRepository;

    @Override
    public ResponseResult insertOrder(DeliveryOrderDto deliveryOrderDto) {
        SnowFlake snowFlaker = new SnowFlake(1, 3);
        //雪花生成商品id
        Long commodityId = snowFlaker.nextId();
        //雪花生成订单id
        Long deliveryOrderId = snowFlaker.nextId();
        log.info(String.valueOf(commodityId));
        log.info(String.valueOf(deliveryOrderId));

        //插入商品数据
        Commodity commodity = Commodity.builder().id(commodityId).priceRang(deliveryOrderDto.getPriceRang())
                .type(deliveryOrderDto.getType()).gmtCreate(Timestamp.valueOf(LocalDateTime.now())).gmtModified(Timestamp.valueOf(LocalDateTime.now())).isDeleted(false).build();
        Commodity save = commondityRepository.save(commodity);

        if (save != null) {
            //插入订单数据
            log.info(String.valueOf(deliveryOrderDto));
            DeliveryOrder deliveryOrder = DeliveryOrder.builder()
                    .id(deliveryOrderId)
                    .deliveryTime(DateTest.getLocalDateTime(deliveryOrderDto.getDeliveryTime())).amount(deliveryOrderDto.getAmount())
                    .commodityId(commodityId)
                    .dDimension(deliveryOrderDto.getDdimension())
                    .destination(deliveryOrderDto.getDestination())
                    .dLongitude(deliveryOrderDto.getDlongitude())
                    .founderId(deliveryOrderDto.getFounderId())
                    .founderName(deliveryOrderDto.getFounderName()).founderPhonenumber(deliveryOrderDto.getFounderPhonenumer())
                    .status(0)
                    .oderCreateTime(Timestamp.valueOf(LocalDateTime.now())).oDimension(deliveryOrderDto.getOdimension())
                    .oLongitude(deliveryOrderDto.getOlongitude())
                    .originAddress(deliveryOrderDto.getOriginAddress())
                    .receiverName(deliveryOrderDto.getReceiverName())
                    .receiverPhoneNumber(deliveryOrderDto.getReceiverPhoneNumber())
                    .remark(deliveryOrderDto.getRemark())
                    .gmtCreate(Timestamp.valueOf(LocalDateTime.now())).gmtModified(Timestamp.valueOf(LocalDateTime.now())).isDeleted(false)

                    .build();
            DeliveryOrder deliveryOrderSave = deliveryOderRepository.save(deliveryOrder);
            if (deliveryOrderSave != null) {
                return ResponseResult.success();
            } else {
                return ResponseResult.failure(ResultCode.DATABASE_ERROR);
            }
        }
        return ResponseResult.failure(ResultCode.DATABASE_ERROR);
    }

    @Override
    public ResponseResult cancleOrder(Long id) {
        //根居id查询出来的交易表是否有数据
        Transaction transaction = transactionMapper.selectById(id);
        if (transaction == null) {
            //没有数据
            //查询出来交易表中的id
            QueryWrapper<DeliveryOrder> deliveryOrderQueryWrapper = new QueryWrapper<>();
            DeliveryOrder deliveryOrder = DeliveryOrder.builder().status(1).build();
            deliveryOrderQueryWrapper.eq("id", id);
            int update = deliveryOrderMapper.update(deliveryOrder, deliveryOrderQueryWrapper);
            if (update != 0) {
                SnowFlake snowFlake = new SnowFlake(1, 3);
                long cancleId = snowFlake.nextId();
                CancleDeliveryOrder cancleDeliveryOrder = CancleDeliveryOrder.builder().cancleTime(LocalDateTime.now()).id(cancleId).oderId(id).isDeleted(true).gmtCreate(LocalDateTime.now()).gmtModified(LocalDateTime.now()).build();
                cancleDeliveryOderRepository.save(cancleDeliveryOrder);
                return ResponseResult.success();
            }
        }
        return ResponseResult.failure(ResultCode.ORDER_NOT_CANCLE);
    }

    @Override
    public Map<String, Object> selectFinshOrder(FinshOrderDto finshOrderDto) {
        IPage<DeliveryOrder> page = new Page<>(finshOrderDto.getNum(), finshOrderDto.getSize());
        IPage<DeliveryOrder> pages;
        if (finshOrderDto.getStatus() != null) {
            QueryWrapper<DeliveryOrder> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("founder_id", finshOrderDto.getFounderId()).eq("status", finshOrderDto.getStatus()).orderByDesc("oder_create_time").eq("is_deleted", false);
            pages = deliveryOrderMapper.selectPage(page, queryWrapper);
            log.info(String.valueOf(pages.getRecords()));
        } else {
            QueryWrapper<DeliveryOrder> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("founder_id", finshOrderDto.getFounderId())
                    .orderByDesc("oder_create_time").eq("is_deleted", false);
            pages = deliveryOrderMapper.selectPage(page, queryWrapper);
        }
        //获取总页数
        Long totalPage = pages.getPages();
        //获取数据
        List<DeliveryOrder> records = pages.getRecords();
        Map<String, Object> map = new HashMap<>();
        map.put("totalPage", totalPage);
        map.put("records", records);
        return map;
    }

    @Override
    public ResponseResult deleteOrder(Long id) {
        QueryWrapper<DeliveryOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id).ne("status", 2);
        DeliveryOrder deliveryOrder = DeliveryOrder.builder().isDeleted(true).build();
        deliveryOrderMapper.update(deliveryOrder, queryWrapper);
        return ResponseResult.success();
    }


}
