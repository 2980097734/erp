package com.guigu.erp.controller.m;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guigu.erp.domain.m.Apply;
import com.guigu.erp.service.m.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class ApplyController {
    @Autowired
    ApplyService applyService;

    //查询
    @RequestMapping("queryAllApply.action")
    public List<Apply> queryAllApply(){
        return applyService.All();
    }
    //添加
    @RequestMapping("addApply.action")
    public boolean addApply(Apply apply){
        return applyService.save(apply);
    }
    //查询未审核的生产计划
//    @RequestMapping("queryByCheckTag.action")
//    public List<Apply> queryByCheckTag(String checkTag){
//        QueryWrapper<Apply> queryWrapper = new QueryWrapper<Apply>();
//        queryWrapper.eq("checkTag",checkTag);
//        return null;
//    }

}
