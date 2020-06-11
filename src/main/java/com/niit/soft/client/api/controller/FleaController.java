package com.niit.soft.client.api.controller;

import com.niit.soft.client.api.annotation.ControllerWebLog;
import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.*;
import com.niit.soft.client.api.domain.model.FleaGoods;
import com.niit.soft.client.api.domain.model.FleaReward;
import com.niit.soft.client.api.repository.FleaCommentRepository;
import com.niit.soft.client.api.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName FleaController.java
 * @Description TODO
 * @createTime 2020年06月09日 14:37:00
 */
@Slf4j
@RestController
@RequestMapping(value = "/flea/")
@Api(value = "FleaController", tags = {"跳蚤市场接口"})
public class FleaController {
    @Resource
    private FleaGoodsService fleaGoodsService;
    @Resource
    private FleaRewardService fleaRewardService;
    @Resource
    private FleaTypeService fleaTypeService;
    @Resource
    private FleaUserService fleaUserService;
    @Resource
    private FleaOrderService fleaOrderService;
    @Resource
    private FleaCollectionService fleaCollectionService;
    @Resource
    private FleaCommentService fleaCommentService;
    /**
     * 根据搜索框的输入模糊查询  商品名，标签，或 悬赏
     */
    @ApiOperation(value = "模糊搜索", notes = "根据搜索框的输入模糊查询  商品名，标签，或 悬赏")
    @PostMapping("search")
    @ControllerWebLog(name = "searchByContent", isSaved = true)
    ResponseResult searchByContent(@RequestBody FleaSearchDto fleaSearchDto) {
        log.info("-----searchByContent-----请求参数：" + fleaSearchDto + "**1**");
        Map<String, Object> map = new HashMap<String, Object>();
        //查询符合的商品
        final Page<FleaGoods> fleaGoodsByContent = fleaGoodsService.findFleaGoodsByContent(fleaSearchDto);
        //查询符合的悬赏
        final Page<FleaReward> fleaRewardByContent = fleaRewardService.findFleaRewardByContent(fleaSearchDto);
        map.put("fleaGoods", fleaGoodsByContent);
        map.put("fleaReward", fleaRewardByContent);
        return ResponseResult.success(map);
    }

    /**
     * 查找所有分类
     *
     * @return
     */
    @ApiOperation(value = "查询所有分类", notes = "分为一级分类和二级分类")
    @PostMapping("type/all")
    @ControllerWebLog(name = "findAllType", isSaved = true)
    ResponseResult findAllType() {
        return ResponseResult.success(fleaTypeService.findAllType());
    }

    /**
     * 查找所有悬赏
     *
     * @return
     */
    @ApiOperation(value = "查询所有悬赏", notes = "返回所有的悬赏")
    @PostMapping("reward/all")
    @ControllerWebLog(name = "findRewardType", isSaved = true)
    ResponseResult findRewardType(@RequestBody PageDto pageDto) {
        log.info("-----findRewardType-----请求参数：" + pageDto + "**1**");
        return ResponseResult.success(fleaRewardService.findAll(pageDto));
    }


    @ControllerWebLog(name = "getTopTwoReward", isSaved = true)
    @ApiOperation(value = "查询悬赏最新的前两条数据", notes = "不需要参数")
    @PostMapping("reward/top")
    public ResponseResult getTopTwoReward() {
        log.info("进入查询悬赏数据接口");
        return fleaRewardService.getRewardTopThree();
    }

    @ControllerWebLog(name = "getGoodsByTime", isSaved = true)
    @ApiOperation(value = "查询商品信息，根据时间排序", notes = "pageDto分页参数，参数从0页开始")
    @PostMapping("goods/all")
    public ResponseResult getGoodsByTime(@RequestBody PageDto pageDto) {
        log.info("进入查询商品信息接口，传来的分页参数为: pageDto", pageDto + "**1**");
        return fleaGoodsService.getGoodsByTime(pageDto);
    }

    @ControllerWebLog(name = "getGoodsByType", isSaved = true)
    @ApiOperation(value = "根据类别ID查询商品信息", notes = "pageDto分页参数和typeId类别ID，参数从0页开始")
    @PostMapping("goods/type")
    public ResponseResult getGoodsByType(@RequestBody TypeDto typeDto) {
        log.info("进入根据类型查询商品接口，传来的参数为：pageDtp,typeId", typeDto);
        return fleaTypeService.getGoodsByType(typeDto);
    }

    @ControllerWebLog(name = "findGoodById", isSaved = true)
    @ApiOperation(value = "根据商品id查询指定商品的详细信息", notes = "请求参数为商品id----goodId   ")
    @PostMapping(value = "goods/id")
    public ResponseResult findGoodById(@RequestBody GoodIdDto goodId) {
        log.info("访问goods/id接口");
        log.info("-----goods/id-----请求参数：" + goodId + "**1**");
        return fleaGoodsService.findGoodById(goodId);
    }

    /**
     * 修改商品信息
     *
     * @param fleaGoodsDto FleaGoodsDto
     * @return ResponseResult
     */
    @ControllerWebLog(name = "updateGoodMessage", isSaved = true)
    @ApiOperation(value = "修改商品信息", notes = "请求参数为商品类、发布人id、类型id----fleaGoods、userId、typeId  ")
    @PostMapping(value = "goods/set")
    public ResponseResult updateGoodMessage(@RequestBody FleaGoodsDto fleaGoodsDto) {
        log.info("访问goods/set接口");
        log.info("-----goods/set-----请求参数：" + fleaGoodsDto + "**1**");
        return fleaGoodsService.updateGood(fleaGoodsDto);
    }

    /**
     * 下架商品
     *
     * @param soldOutGoodDto SoldOutGoodDto
     * @return ResponseResult
     */
    @ControllerWebLog(name = "soldOutGood", isSaved = true)
    @ApiOperation(value = "下架商品", notes = "请求参数为删除标志、商品id----isDeleted、goodId  ")
    @PostMapping(value = "goods/delete")
    public ResponseResult soldOutGood(@RequestBody SoldOutGoodDto soldOutGoodDto) {
        log.info("访问goods/delete接口");
        log.info("-----goods/delete-----请求参数：" + "**1**");
        return fleaGoodsService.soldOutGood(soldOutGoodDto);
    }

    /**
     * 添加商品
     *
     * @param saveGoodDto SaveGoodDto
     * @return ResponseResult
     */
    @ControllerWebLog(name = "saveGoods", isSaved = true)
    @ApiOperation(value = "添加商品", notes = "请求参数为商品Dto----fleaGoodsDto  ")
    @PostMapping(value = "goods/increased")
    public ResponseResult saveGoods(@RequestBody SaveGoodDto saveGoodDto) {
        log.info("访问goods/increased接口");
        log.info("-----goods/increased-----请求参数：" + saveGoodDto + "**1**");
        return fleaGoodsService.saveGoods(saveGoodDto);
    }

    /**
     * 用户数据添加
     *
     * @param fleaUserDto FleaUserDto
     * @return ResponseResult
     */
    @ControllerWebLog(name = "saveFleaUser", isSaved = true)
    @ApiOperation(value = "添加用户数据", notes = "请求参数为用户Dto----fleaUserDto  ")
    @PostMapping(value = "users/saving")
    public ResponseResult saveFleaUser(@RequestBody FleaUserDto fleaUserDto) {
        log.info("访问users/saving接口");
        log.info("-----users/saving-----请求参数：" + fleaUserDto + "**1**");
        return fleaUserService.saveFleaUser(fleaUserDto);
    }

    /**
     * 修改用户数据
     *
     * @param updateFleaUserDto UpdateFleaUserDto
     * @return ResponseResult
     */
    @ControllerWebLog(name = "updateFleaUser", isSaved = true)
    @ApiOperation(value = "修改用户数据", notes = "请求参数为修改用户Dto----updateFleaUserDto  ")
    @PostMapping(value = "users/flushing")
    public ResponseResult updateFleaUser(@RequestBody UpdateFleaUserDto updateFleaUserDto) {
        log.info("访问users/flushing接口");
        log.info("-----users/flushing-----请求参数：" + updateFleaUserDto + "**1**");
        return fleaUserService.updateFleaUser(updateFleaUserDto);
    }

    @ControllerWebLog(name = "addCollection", isSaved = true)
    @ApiOperation(value = "新增收藏", notes = "请求参数为商品ID以及收藏用户ID")
    @PostMapping("/collection/increased")
    public ResponseResult addCollection(@RequestBody CollectionDto collectionDto) {
        log.info("访问收藏新增接口,请求参数为：", collectionDto);
        return fleaCollectionService.addCollection(collectionDto);
    }

    @ControllerWebLog(name = "getAll", isSaved = true)
    @ApiOperation(value = "查询所有收藏", notes = "没有请求参数，直接post提交")
    @PostMapping("collection/all")
    public ResponseResult getAll() {
        log.info("进入获取所有收藏接口");
        return fleaCollectionService.getCollection();
    }

    @ControllerWebLog(name = "logicalDel", isSaved = true)
    @ApiOperation(value = "逻辑删除收藏", notes = "请求参数为商品ID与用户ID----collectionDto  ")
    @PostMapping("collection/deleted")
    public ResponseResult logicalDel(@RequestBody CancelCollectionDto collectionDto) {
        log.info("进入逻辑删除收藏部分，传来的参数为：,{}**1**", collectionDto);
        return fleaCollectionService.logicalDel(collectionDto);
    }


    @ControllerWebLog(name = "rewardIncreased", isSaved = true)
    @ApiOperation(value = "添加悬赏", notes = "帖子id，随便给，传了不用，凑数的,描述，图片地址，描述，标题，悬赏人id")
    @PostMapping("reward/increased")
    public ResponseResult rewardIncreased(@RequestBody FleaRewardDto fleaRewardDto) {
        log.info("-----reward/increased-----请求参数：" + fleaRewardDto + "**1**");
        return fleaRewardService.save(fleaRewardDto);
    }

    @ControllerWebLog(name = "rewardUpdated", isSaved = true)
    @ApiOperation(value = "修改悬赏", notes = "帖子id.图片地址,标题，悬赏人id")
    @PostMapping("reward/updated")
    public ResponseResult rewardUpdated(@RequestBody FleaRewardDto fleaRewardDto) {
        log.info("-----reward/updated-----请求参数：" + fleaRewardDto + "**1**");
        return fleaRewardService.update(fleaRewardDto);
    }

    @ControllerWebLog(name = "rewardDeleted", isSaved = true)
    @ApiOperation(value = "删除悬赏", notes = "描述，图片地址，描述，标题，悬赏人id")
    @PostMapping("reward/deleted")
    public ResponseResult rewardSet(@RequestBody RewardDto rewardDto) {
        log.info("-----reward/deleted-----请求参数：" + rewardDto + "**1**");
        return fleaRewardService.delete(rewardDto.getRewardId());
    }

    @ControllerWebLog(name = "orderIncreased", isSaved = true)
    @ApiOperation(value = "添加订单", notes = "订单编号，商品id，商家id，用户id")
    @PostMapping("order/increased")
    public ResponseResult orderIncreased(@RequestBody FleaOrderDto fleaOrderDto) {
        log.info("-----order/increased----请求参数：" + fleaOrderDto + "**1**");
        return fleaOrderService.orderIncreased(fleaOrderDto);
    }

    @ControllerWebLog(name = "orderDel", isSaved = true)
    @ApiOperation(value = "逻辑删除商品", notes = "商品ID以及买家ID")
    @PostMapping("order/deleted")
    public ResponseResult orderDel(@RequestBody FleaOrderDto fleaOrderDto) {
        log.info("-----order/deleted----请求参数：" + fleaOrderDto + "**1**");
        return fleaOrderService.logicalDel(fleaOrderDto);
    }

    @ControllerWebLog(name = "addComment", isSaved = true)
    @ApiOperation(value = "添加评论", notes = "商品ID以及买家ID")
    @PostMapping("/comment/increased")
    public ResponseResult addComment(@RequestBody FleaCommentDto fleaCommentDto) {
        log.info("-----order/deleted----请求参数：" + fleaCommentDto + "**1**");
        return fleaCommentService.addComment(fleaCommentDto);
    }

}
