package com.github.skyisbule.chain.controller;

import com.github.skyisbule.chain.common.BaseHttpResponse;
import com.github.skyisbule.chain.domain.StuInfo;
import com.github.skyisbule.chain.exception.GlobalException;
import com.github.skyisbule.chain.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/stu",method = {RequestMethod.GET,RequestMethod.POST})
@Api("学生的接口")
public class StuController {

    @Autowired
    StudentService service;

    @ApiOperation("添加一个学生")
    @RequestMapping("/add")
    public BaseHttpResponse<String> commit(StuInfo stuInfo) throws GlobalException {
        try{
            return new BaseHttpResponse<>(service.add(stuInfo));
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
    }

    @ApiOperation("根据学校或者直接列出所有学生的列表")
    @RequestMapping("/list")
    public BaseHttpResponse<List<StuInfo>> list(int page,int size,String school) throws GlobalException {
        try{
            return new BaseHttpResponse<>(service.list(page,size,school));
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
    }

    @ApiOperation("根据交易hash得到一个学生的信息")
    @RequestMapping("/getByHash")
    public BaseHttpResponse<StuInfo> getByHash(String hash) throws GlobalException {
        try{
            return new BaseHttpResponse<>(service.getByHash(hash));
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
    }

}
