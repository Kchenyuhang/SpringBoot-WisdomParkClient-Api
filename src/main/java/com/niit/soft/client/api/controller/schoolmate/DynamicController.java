package com.niit.soft.client.api.controller.schoolmate;

import com.niit.soft.client.api.annotation.ControllerWebLog;
import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.DynamicCollectionInDto;
import com.niit.soft.client.api.domain.dto.PageDto;
import com.niit.soft.client.api.domain.dto.schoolmate.*;
import com.niit.soft.client.api.domain.model.schoolmate.Dynamic;
import com.niit.soft.client.api.domain.vo.schoolmate.DynamicVo;
import com.niit.soft.client.api.service.schoolmate.CollectionsService;
import com.niit.soft.client.api.service.schoolmate.CommentService;
import com.niit.soft.client.api.service.schoolmate.DynamicService;
import com.niit.soft.client.api.service.schoolmate.ReplyCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * @ClassName DynamicController
 * @Description 好友圈模块动态资讯
 * @Author xiaobinggan
 * @Date 2020/6/8 8:14 下午
 * @Version 1.0
 **/
@Slf4j
@RestController
@RequestMapping("/dynamic")
@Api(tags = "校友圈的接口")
public class DynamicController {
    @Resource
    private DynamicService dynamicService;

    @Resource
    private CommentService commentService;

    @Resource
    private ReplyCommentService replyCommentService;

    @Resource
    private CollectionsService collectionsService;

    @PostMapping("/")
    @ControllerWebLog(name = "findDynamicVoById", isSaved = true)
    @ApiOperation(value = "根据id查找动态资讯", notes = "请求参数为动态id")
    ResponseResult findDynamicVoById(@RequestBody IdDto id) {
        return ResponseResult.success(dynamicService.findDynamicVoById(id.getId()));
    }

    @PostMapping("/photo")
    @ControllerWebLog(name = "findDynamicPhotoVoById", isSaved = true)
    @ApiOperation(value = "根据id查找动态资讯的图片", notes = "请求参数为动态id")
    ResponseResult findDynamicPhotoVoById(@RequestBody IdDto id) {
        return ResponseResult.success(dynamicService.findDynamicPhotoById(id.getId()));
    }

    @PostMapping("/photo/new")
    @ControllerWebLog(name = "findDynamicPhotoVoById", isSaved = true)
    @ApiOperation(value = "加添图片", notes = "请求参数为动态id和图片地址")
    ResponseResult addDynamicPhoto(@RequestBody List<DynamicPhotoDto> dynamicPhotoDtos) {
        return ResponseResult.success(dynamicService.addPhoto(dynamicPhotoDtos));
    }


    @PostMapping("/thumbsup")
    @ControllerWebLog(name = "thumbsUp", isSaved = true)
    @ApiOperation(value = "点赞", notes = "请求参数为thumbDto 再次点击为取消点赞")
    ResponseResult thumbsUp(@RequestBody ThumbDto thumbDto) {
        return dynamicService.thumbsUp(thumbDto);
    }


    @PostMapping
    @ControllerWebLog(name = "findDynamic", isSaved = true)
    @ApiOperation(value = "查找所有动态资讯", notes = "请求参数为传递分页参数")
    ResponseResult findDynamic(@RequestBody SchoolmatePageDto schoolmatePageDto) {
        List<Dynamic> dynamicByPage = dynamicService.findDynamicByPage(schoolmatePageDto);
        List<DynamicVo> dynamicVos = new ArrayList<>(10);
        for (Dynamic dynamic : dynamicByPage) {
            dynamicVos.add(dynamicService.findDynamicVoById(dynamic.getPkDynamicId()));
        }
        return ResponseResult.success(dynamicVos);
    }

    @PostMapping("/new")
    @ControllerWebLog(name = "findDynamic", isSaved = true)
    @ApiOperation(value = "发表动态资讯", notes = "请求参数为具体动态内容")
    ResponseResult addOne(@RequestBody DynamicDto dynamicDto) {
        return ResponseResult.success(dynamicService.addOne(dynamicDto));
    }

    @PostMapping("/comment")
    @ControllerWebLog(name = "findCommentVoById", isSaved = true)
    @ApiOperation(value = "好友圈根据评论id查找多级评论", notes = "请求参数为评论id")
    ResponseResult findCommentVoById(@RequestBody IdDto id) {
        return ResponseResult.success(dynamicService.findCommentVoById(id.getId()));
    }

    @ApiOperation(value = "添加评论", notes = "传递参数为内容，动态id，用户id")
    @PostMapping(value = "/comment/insert")
    public ResponseResult insertComment(@RequestBody CommentDto commentDto) {
        return commentService.insertComment(commentDto);
    }

    /**
     * 删除校友评论
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除校友评论", notes = "传递参数为comment的id")
    @PostMapping(value = "/comment/deletion")
    public ResponseResult deleteComment(@RequestBody IdDto id) {
        return commentService.deleteComment(id.getId());
    }

    /**
     * 添加校友圈动态评论
     *
     * @param replyCommentDto
     * @return
     */
    @ControllerWebLog(name = "insertReplyComment", isSaved = true)
    @ApiOperation(value = "添加评论的回复", notes = "传递参数为内容，评论id，用户id")
    @PostMapping(value = "/replyComment/insert")
    public ResponseResult insertReplyComment(@RequestBody ReplyCommentDto replyCommentDto) {
        return replyCommentService.insertReplyComment(replyCommentDto);
    }

    /**
     * 删除校友评论
     *
     * @param id
     * @return
     */
    @ControllerWebLog(name = "deleteReplyComment", isSaved = true)
    @ApiOperation(value = "删除评论的回复", notes = "传递参数为reply_comment的id")
    @PostMapping(value = "/replyComment/deletion")
    public ResponseResult deleteReplyComment(@RequestBody IdDto id) {
        return replyCommentService.deleteReplyComment(id.getId());
    }

    /**
     * 查询用户以收藏的动态资讯
     *
     * @param pageDto
     * @return
     */
    @ControllerWebLog(name = "getCollectionsByUserId", isSaved = true)
    @ApiOperation(value = "查询用户以收藏的动态资讯", notes = "field参数需要填写一个数字字符串，代表用户id")
    @PostMapping(value = "/Collection")
    public ResponseResult getCollectionsByUserId(@RequestBody PageDto pageDto) {
        return collectionsService.getCollectionsByUserId(pageDto);
    }

    /**
     * 添加加收藏
     *
     * @param dynamicCollectionInDto
     * @return
     */
    @ControllerWebLog(name = "insertCollections", isSaved = true)
    @ApiOperation(value = "添加用户以收藏的动态资讯", notes = "参数为：用户id，动态id，一个用户不可重复添加同一个动态")
    @PostMapping(value = "/Collection/insert")
    public ResponseResult insertCollections(@RequestBody DynamicCollectionInDto dynamicCollectionInDto) {
        return collectionsService.insertCollections(dynamicCollectionInDto);
    }

    /**
     * 删除加收藏
     *
     * @param id
     * @return
     */
    @ControllerWebLog(name = "updateCollectionsIsDelete", isSaved = true)
    @ApiOperation(value = "删除用户以收藏的动态资讯", notes = "参数为：收藏id")
    @PostMapping(value = "/Collection/deletion")
    public ResponseResult updateCollectionsIsDelete(@RequestBody IdDto id) {
        return collectionsService.updateCollectionsIsDelete(id.getId());
    }

}
