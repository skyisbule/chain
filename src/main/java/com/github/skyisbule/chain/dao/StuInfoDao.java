package com.github.skyisbule.chain.dao;

import com.github.skyisbule.chain.domain.StuInfo;
import com.github.skyisbule.chain.domain.StuInfoExample;
import org.springframework.stereotype.Repository;

/**
 * StuInfoDao继承基类
 */
@Repository
public interface StuInfoDao extends MyBatisBaseDao<StuInfo, Integer, StuInfoExample> {
}