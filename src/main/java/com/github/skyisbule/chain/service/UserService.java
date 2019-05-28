package com.github.skyisbule.chain.service;

import cn.hutool.http.HttpRequest;
import com.github.skyisbule.chain.common.ErrorConstant;
import com.github.skyisbule.chain.dao.UserDao;
import com.github.skyisbule.chain.domain.User;
import com.github.skyisbule.chain.domain.UserExample;
import com.github.skyisbule.chain.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    @Value("${server.url}")
    private String apiUrl;

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
                throw new GlobalException(ErrorConstant.FAIL_REGISTER);
            }
        }
        return null;
    }

    public void doLogin(String userName,String password) throws GlobalException {
        if (userName == null || password == null)
            throw new GlobalException(ErrorConstant.ACCOUNT_OR_PASSWD_ERROR);

    }

}
