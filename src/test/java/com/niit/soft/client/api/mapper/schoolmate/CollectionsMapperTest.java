package com.niit.soft.client.api.mapper.schoolmate;

import com.niit.soft.client.api.domain.dto.DynamicCollectionDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@SpringBootTest
class CollectionsMapperTest {
    @Resource
    private CollectionsMapper collectionsMapper;

    @Test
    void findCollectionsByUserId() {
        List<Map<String, Object>> list = collectionsMapper.findCollectionsByUserId("1");
        System.out.println(list);
    }

    @Test
    void findCollectionsByDynamicId() {
        DynamicCollectionDto dynamicCollectionDto = collectionsMapper.findCollectionsByDynamicId("1271709711276052480");
        System.out.println(dynamicCollectionDto);

    }
}