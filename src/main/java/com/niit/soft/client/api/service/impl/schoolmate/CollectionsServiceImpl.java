package com.niit.soft.client.api.service.impl.schoolmate;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.DynamicCollectionDto;
import com.niit.soft.client.api.domain.dto.PageDto;
import com.niit.soft.client.api.domain.model.schoolmate.Collections;
import com.niit.soft.client.api.mapper.schoolmate.CollectionsMapper;
import com.niit.soft.client.api.repository.schoolmate.CollectionsRepository;
import com.niit.soft.client.api.repository.schoolmate.DynamicPhotoRepository;
import com.niit.soft.client.api.service.schoolmate.CollectionsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yujie_Zhao
 * @ClassName CollectionsServiceImpl
 * @Description 收藏
 * @Date 2020/6/11  9:49
 * @Version 1.0
 **/
@Service
public class CollectionsServiceImpl implements CollectionsService {
    @Resource
    private CollectionsMapper collectionsMapper;

    @Resource
    private CollectionsRepository collectionsRepository;

    @Resource
    private DynamicPhotoRepository dynamicPhotoRepository;

    @Override
    public ResponseResult findCollections(String id) {
        collectionsMapper.findCollectionsByUserId(id);
        return null;
    }

    @Override
    public ResponseResult getCollectionsByUserId(PageDto pageDto) {
        List<DynamicCollectionDto> collectionDtoArrayList = new ArrayList<>();

        Pageable pageable = PageRequest.of(
                pageDto.getCurrentPage(),
                pageDto.getPageSize(),
                Sort.Direction.ASC,
                "pk_collection_id");
        Page<Collections> collectionsPage =
                collectionsRepository.getCollectionsByUserId((String) pageDto.getField(), pageable);
        collectionsPage.forEach(collections -> {
            DynamicCollectionDto dynamicCollectionDto = collectionsMapper.findCollectionsByDynamicId(collections.getDynamicId());
            //得到一个资讯的所有配图
            List<String> dynamicPhotoList = dynamicPhotoRepository.findDistinctByDynamicId(collections.getDynamicId());
            if (dynamicPhotoList != null) {
                dynamicCollectionDto.setPicture(dynamicPhotoList);
            }
            collectionDtoArrayList.add(dynamicCollectionDto);
        });
        return ResponseResult.success(collectionDtoArrayList);
    }
}