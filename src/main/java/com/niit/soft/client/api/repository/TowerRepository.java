package com.niit.soft.client.api.repository;

import com.niit.soft.client.api.domain.model.Room;
import com.niit.soft.client.api.domain.model.Tower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName TowerRepository
 * @Description TODO
 * @Author 田震
 * @Date 2020/6/9
 **/
public interface TowerRepository extends JpaRepository<Tower, Long> {

    /**
     * 批量删除
     * @param ids
     */
    @Modifying
    @Transactional(timeout = 10,rollbackFor = RuntimeException.class)
    @Query("update InfoManage v set v.isDeleted = true where v.pkInfoManageId in ?1")
    void deleteBatch(List<Long> ids);

    /**
     * 查询寝室
     * @return
     */
    @Query(" SELECT u FROM Tower a "+" LEFT JOIN  Room  b "+"ON a.pkTowerId=b.towerId "+"LEFT JOIN TowerUnit u "+
            "ON b.unitId = u.unitId WHERE  a.type =1")
    List<Room> findBedRoom();
}