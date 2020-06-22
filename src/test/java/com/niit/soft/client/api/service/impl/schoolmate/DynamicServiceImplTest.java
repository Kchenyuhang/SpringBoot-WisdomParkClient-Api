package com.niit.soft.client.api.service.impl.schoolmate;

import com.niit.soft.client.api.domain.dto.schoolmate.DynamicPhotoDto;
import com.niit.soft.client.api.domain.model.schoolmate.DynamicPhoto;
import com.niit.soft.client.api.service.schoolmate.DynamicService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;

@SpringBootTest
class DynamicServiceImplTest {
    @Resource
    private DynamicService dynamicService;

    @Test
    void addPhoto() {
        ArrayList<DynamicPhotoDto> dynamicPhotos = new ArrayList<>();
        dynamicPhotos.add(DynamicPhotoDto.builder().dynamicId("123").picture("123").build());
        dynamicPhotos.add(DynamicPhotoDto.builder().dynamicId("321").picture("312").build());
        System.out.println(dynamicService.addPhoto(dynamicPhotos));
    }
}