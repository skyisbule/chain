package com.github.skyisbule.chain.dao;

import com.github.skyisbule.chain.domain.Approval;
import com.github.skyisbule.chain.domain.ApprovalExample;
import org.springframework.stereotype.Repository;

/**
 * ApprovalDao继承基类
 */
@Repository
public interface ApprovalDao extends MyBatisBaseDao<Approval, Integer, ApprovalExample> {
}