package com.niit.soft.client.api.service;


import java.util.Map;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName FleaGoodsService.java
 * @Description TODO
 * @createTime 2020年06月09日 13:58:00
 */
public interface FleaTypeService {
    /**
     * 查询所有的类型
     * @return
     */
    Map<String,Object>  findAllType();


}
