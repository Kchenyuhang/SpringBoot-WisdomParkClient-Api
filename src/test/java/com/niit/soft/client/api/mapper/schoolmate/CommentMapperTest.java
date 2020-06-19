package com.niit.soft.client.api.mapper.schoolmate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentMapperTest {
    @Resource
    private CommentMapper commentMapper;
    @Test
    void findCommentVoById() {
        System.out.println(commentMapper.findCommentVoById("57487070822010880"));
    }
}