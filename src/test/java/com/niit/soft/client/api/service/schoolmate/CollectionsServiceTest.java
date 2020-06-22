package com.niit.soft.client.api.service.schoolmate;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.schoolmate.DynamicCollectionInDto;
import com.niit.soft.client.api.domain.dto.PageDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class CollectionsServiceTest {


    @Resource
    private CollectionsService collectionsService;

    @Test
    void findCollections() {
    }

    @Test
    void getCollectionsByUserId() {
        PageDto pageDto = PageDto.builder()
                .currentPage(0)
                .pageSize(2)
                .field("1")
                .build();
        ResponseResult result = collectionsService.getCollectionsByUserId(pageDto);
        System.out.println(result);
    }

    @Test
    void insertCollections() {
        DynamicCollectionInDto dynamicCollectionInDto = DynamicCollectionInDto.builder()
                .userId("0ff319a1-a3a0-463b-a63a-61df28ad28a9")
                .dynamicId("1271709712223965184")
                .build();
        collectionsService.insertCollections(dynamicCollectionInDto);
    }

    @Test
    void updateCollectionsIsDelete() {
        collectionsService.updateCollectionsIsDelete("55969787893125120");
    }
}