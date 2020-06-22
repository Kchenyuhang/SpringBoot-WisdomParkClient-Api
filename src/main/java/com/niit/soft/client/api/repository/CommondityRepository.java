package com.niit.soft.client.api.repository;

import com.niit.soft.client.api.domain.model.Clazz;
import com.niit.soft.client.api.domain.model.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wl
 * @ClassNameCommondityRepository
 * @Description TODO
 * @Date 2020/6/9
 * @Version 1.0
 */
public interface CommondityRepository extends JpaRepository<Commodity, Long> {
}
