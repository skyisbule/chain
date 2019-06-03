package com.github.skyisbule.chain.service;

import cn.hutool.http.HttpRequest;
import com.github.skyisbule.chain.common.ErrorConstant;
import com.github.skyisbule.chain.common.Security;
import com.github.skyisbule.chain.dao.StuInfoDao;
import com.github.skyisbule.chain.dao.UserDao;
import com.github.skyisbule.chain.domain.StuInfo;
import com.github.skyisbule.chain.domain.StuInfoExample;
import com.github.skyisbule.chain.domain.User;
import com.github.skyisbule.chain.domain.UserExample;
import com.github.skyisbule.chain.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    StuInfoDao stuInfoDao;
    @Autowired
    UserDao    userDao;

    @Value("${server.url}")
    private String apiUrl;

    @Transactional(rollbackFor = Exception.class)
    public String add(StuInfo info) throws GlobalException, UnsupportedEncodingException {
        String infoStr = info.getAll();
        info.setSchool(java.net.URLDecoder.decode(info.getSchool(),"UTF-8"));
        info.setStuName(java.net.URLDecoder.decode(info.getStuName(),"UTF-8"));
        try{
            String exchangeHash = HttpRequest.post(apiUrl+"/add")
                    .form("info",infoStr)
                    .timeout(20000)//超时，毫秒
                    .execute().body();
            info.setExangeHash(exchangeHash);
        }catch (Exception e){
            info.setExangeHash(Security.getTransactionHash());
            //throw new GlobalException(ErrorConstant.FAIL_REGISTER);
        }
        stuInfoDao.insert(info);
        UserExample e = new UserExample();
        e.createCriteria()
                .andUserNameEqualTo(info.getSchool());
        User user = userDao.selectByExample(e).get(0);
        if (user.getBalance() == 0) throw new RuntimeException("zero");
        user.setBalance(user.getBalance()-1);
        userDao.updateByPrimaryKey(user);
        return "success";
    }

    public StuInfo getByHash(String hash) throws GlobalException {
        StuInfo info = new StuInfo();
        info.setSid(0);
        try{
            String exchangeHash = HttpRequest.post(apiUrl+"/getByHash")
                    .form("hash",hash)
                    .timeout(20000)//超时，毫秒
                    .execute().body();
            String[] infos = exchangeHash.split(" ");
            info.setAll(infos[0],infos[1],infos[2],infos[3]);
        }catch (Exception e){
            throw new GlobalException(ErrorConstant.USER_NOT_EXISTS);
        }
        return info;
    }

    public StuInfo doCheck(String stuId){
        StuInfoExample e = new StuInfoExample();
        e.createCriteria()
                .andIdNumEqualTo(stuId);
        List<StuInfo> infos = stuInfoDao.selectByExample(e);
        if (infos == null || infos.size() == 0){
            StuInfo info = new StuInfo();
            info.setSid(0);
            return info;
        }
        return infos.get(0);
    }

    public List<StuInfo> list(int page, int size, String school){
        StuInfoExample e = new StuInfoExample();
        e.setOffset((long)page*size);
        e.setLimit(size);
        if (school == null || school.trim().length() == 0)
            return stuInfoDao.selectByExample(e);
        e.createCriteria()
                .andSchoolEqualTo(school);
        return stuInfoDao.selectByExample(e);
    }

}
