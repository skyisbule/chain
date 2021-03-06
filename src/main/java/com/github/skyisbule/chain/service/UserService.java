package com.github.skyisbule.chain.service;

import cn.hutool.http.HttpRequest;
import com.github.skyisbule.chain.common.ErrorConstant;
import com.github.skyisbule.chain.common.Security;
import com.github.skyisbule.chain.dao.UserDao;
import com.github.skyisbule.chain.domain.User;
import com.github.skyisbule.chain.domain.UserExample;
import com.github.skyisbule.chain.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    @Value("${server.url}")
    private String apiUrl;

    @Transactional(rollbackFor = Exception.class)
    public User doRegister(User user) throws GlobalException {
        user.setUid(null);
        user.setBalance(0);
        if (user.getUserName() == null)
            throw new GlobalException(ErrorConstant.PARAM_ERROR);
        UserExample ue = new UserExample();
        ue.createCriteria()
                .andUserNameEqualTo(user.getUserName());
        List<User> tableUser = userDao.selectByExample(ue);
        if (tableUser == null || tableUser.size() == 0){
            if (user.getPassword() == null)
                user.setPassword("123456");
            try{
                String account = HttpRequest.post(apiUrl+"/newAccount")
                        .timeout(20000)//超时，毫秒
                        .execute().body();
                user.setAccountHash(account);
                userDao.insert(user);
                return user;
            }catch (Exception e){
                user.setAccountHash(Security.getHash());
                userDao.insert(user);
                return user;
                //throw new GlobalException(ErrorConstant.FAIL_REGISTER);
            }
        }
        return null;
    }

    public User doLogin(String userName,String password) throws GlobalException {
        if (userName == null || password == null)
            throw new GlobalException(ErrorConstant.ACCOUNT_OR_PASSWD_ERROR);
        UserExample e = new UserExample();
        e.createCriteria()
                .andUserNameEqualTo(userName)
                .andPasswordEqualTo(password);
        int count = (int)userDao.countByExample(e);
        if (count < 0){
            throw new GlobalException(ErrorConstant.ACCOUNT_OR_PASSWD_ERROR);
        }
        User user = userDao.selectByExample(e).get(0);
        user.setPassword(Security.encode(password));
        return user;
    }

    public int getBalanceBySchool(String school){
        try{
            UserExample e = new UserExample();
            e.createCriteria()
                    .andUserNameEqualTo(school);
            return userDao.selectByExample(e).get(0).getBalance();
        }catch (Exception e ){
            e.printStackTrace();
        }
        return 0;
    }

}
