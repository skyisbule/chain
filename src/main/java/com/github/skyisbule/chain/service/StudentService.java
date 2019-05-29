package com.github.skyisbule.chain.service;

import cn.hutool.http.HttpRequest;
import com.github.skyisbule.chain.common.ErrorConstant;
import com.github.skyisbule.chain.dao.StuInfoDao;
import com.github.skyisbule.chain.domain.StuInfo;
import com.github.skyisbule.chain.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {

    @Autowired
    StuInfoDao stuInfoDao;

    @Value("${server.url}")
    private String apiUrl;

    @Transactional(rollbackFor = Exception.class)
    public String add(StuInfo info) throws GlobalException {
        String infoStr = info.getAll();
        try{
            String exchangeHash = HttpRequest.post(apiUrl+"/add")
                    .form("info",infoStr)
                    .timeout(20000)//超时，毫秒
                    .execute().body();
            info.setExangeHash(exchangeHash);
        }catch (Exception e){
            throw new GlobalException(ErrorConstant.FAIL_REGISTER);
        }
        stuInfoDao.insert(info);
        return "success";
    }

    public StuInfo getByHash(String hash){
        StuInfo info = new StuInfo();
        info.setSid(0);
        return info;
    }

    public void list(int page,int size,String school){

    }

}
