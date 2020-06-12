package com.niit.soft.client.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.niit.soft.client.api.domain.dto.PageDto;
import com.niit.soft.client.api.domain.model.PartJob;
import com.niit.soft.client.api.mapper.PartJobMapper;
import com.niit.soft.client.api.service.PartJobService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author su
 * @className JobServiceImpl
 * @Description TODO
 * @Date 2020/6/9
 * @Version 1.0
 **/
@Service
public class PartJobServiceImpl extends ServiceImpl<PartJobMapper, PartJob> implements PartJobService {

    @Resource
    private PartJobMapper partJobMapper;


    @Override
    public List<PartJob> findAllByPage(PageDto pageDto) {
        //分页查询
        QueryWrapper<PartJob> wrapper = new QueryWrapper<>();
        IPage<PartJob> page = new Page<>(pageDto.getCurrentPage(), pageDto.getPageSize());
        wrapper.select("pk_part_job_id","name","description","boss_id","boss_name","boss_phone","boss_avatar","workplace","working_date","working_time","pay","pay_type","job_type","number","have","need","is_deleted","gmt_create").eq("is_deleted", 0);
        if ("pay".equals(pageDto.getField())){
            wrapper.orderByDesc(pageDto.getField().toString());
        }else if ("gmt_create".equals(pageDto.getField())){
            wrapper.orderByDesc(pageDto.getField().toString());
        }else {
            wrapper.eq("job_type", pageDto.getField());
        }
//        Page<PartJobVo> page = new Page<>(pageDto.getCurrentPage(), pageDto.getPageSize());
//        System.out.println(pageDto.getField());
        return partJobMapper.selectPage(page, wrapper).getRecords();

    }

    @Override
    public int insertJob(PartJob partJob) {
        partJob.setIsDeleted(false);
        partJob.setGmtCreate(Timestamp.valueOf(LocalDateTime.now()));
        partJob.setGmtModified(Timestamp.valueOf(LocalDateTime.now()));
        return partJobMapper.insert(partJob);
    }


}