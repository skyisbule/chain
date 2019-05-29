package com.github.skyisbule.chain.controller;

import com.github.skyisbule.chain.common.BaseHttpResponse;
import com.github.skyisbule.chain.domain.Approval;
import com.github.skyisbule.chain.exception.GlobalException;
import com.github.skyisbule.chain.service.ApprovalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/approval")
@Api("审批相关的接口")
public class ApprovalController {

    @Autowired
    ApprovalService approvalService;

    @ApiOperation("申请审批")
    @RequestMapping("/commit")
    public BaseHttpResponse<String> commit(Integer userId,Integer num) throws GlobalException {
        try{
            return new BaseHttpResponse<>(approvalService.commitApproval(userId,num));
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
    }

    @ApiOperation("查询申请表")
    @RequestMapping("/list")
    public BaseHttpResponse<List<Approval>> list(Integer show) throws GlobalException {
        try{
            return new BaseHttpResponse<>(approvalService.list(show));
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
    }

    @ApiOperation("进行审批")
    @RequestMapping("/register")
    public BaseHttpResponse<String> doRegister(Integer userId,Integer num) throws GlobalException {
        try{
            return new BaseHttpResponse<>(approvalService.commitApproval(userId,num));
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
    }

}
