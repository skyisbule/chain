package com.github.skyisbule.chain.service;

import com.github.skyisbule.chain.common.ErrorConstant;
import com.github.skyisbule.chain.dao.ApprovalDao;
import com.github.skyisbule.chain.dao.UserDao;
import com.github.skyisbule.chain.domain.Approval;
import com.github.skyisbule.chain.domain.ApprovalExample;
import com.github.skyisbule.chain.domain.User;
import com.github.skyisbule.chain.domain.UserExample;
import com.github.skyisbule.chain.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public class ApprovalService {

    @Autowired
    UserDao userDao;
    @Autowired
    ApprovalDao approvalDao;

    public String commitApproval(String school,Integer num) throws GlobalException, UnsupportedEncodingException {
        if (school == null || num == null || num < 0)
            throw new GlobalException(ErrorConstant.PARAM_ERROR);
        school = java.net.URLDecoder.decode(school,"UTF-8");
        UserExample e = new UserExample();
        e.createCriteria()
                .andUserNameEqualTo(school);
        User user = userDao.selectByExample(e).get(0);
        if (user == null)
            throw new GlobalException(ErrorConstant.NO_PERMISSION);
        Approval approval = new Approval();
        approval.setIsPass(0);
        approval.setUserName(user.getUserName());
        approval.setRequire(num);
        approval.setUid(user.getUid());
        approvalDao.insert(approval);
        return "success";
    }

    //show ->   1 全部 2通过 3不通过
    public List<Approval> list(Integer show){
        if (show == null) show = 1;
        ApprovalExample e = new ApprovalExample();
        if (show == 1){
            return approvalDao.selectByExample(e);
        }
        if (show == 2){
            e.createCriteria()
                    .andIsPassEqualTo(1);
        }
        if (show == 3){
            e.createCriteria()
                    .andIsPassEqualTo(0);
        }
        return null;
    }

    public String doApproval(int aid,int isPass,String comment) throws GlobalException {
        Approval approval = approvalDao.selectByPrimaryKey(aid);
        if (approval == null | isPass<0 | isPass>1)
            throw new GlobalException(ErrorConstant.PARAM_ERROR);
        if (approval.getIsPass() == 1) return "0";
        if (isPass == 0)
            approvalDao.deleteByPrimaryKey(aid);
        approval.setIsPass(isPass);
        approval.setComment(comment);
        User user = userDao.selectByPrimaryKey(approval.getUid());
        user.setBalance(user.getBalance() + approval.getRequire());
        userDao.updateByPrimaryKey(user);
        approvalDao.updateByPrimaryKey(approval);
        approvalDao.deleteByPrimaryKey(aid);
        return "success";
    }

}
