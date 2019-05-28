package com.github.skyisbule.chain.dao;

import com.github.skyisbule.chain.domain.User;
import com.github.skyisbule.chain.domain.UserExample;
import org.springframework.stereotype.Repository;

/**
 * UserDao继承基类
 */
@Repository
public interface UserDao extends MyBatisBaseDao<User, Integer, UserExample> {
}