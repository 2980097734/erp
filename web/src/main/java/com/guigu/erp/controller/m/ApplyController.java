package com.guigu.erp.controller.m;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guigu.erp.domain.IDUtil;
import com.guigu.erp.domain.d.File;
import com.guigu.erp.domain.m.Apply;
import com.guigu.erp.mapper.m.ApplyMapper;
import com.guigu.erp.service.d.FileService;
import com.guigu.erp.service.m.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class ApplyController {
    @Autowired
    ApplyService applyService;
    @Autowired
    FileService fileService;
    @Autowired
    ApplyMapper applyMapper;
    //查询
    @RequestMapping("queryAllApply.action")
    public List<Apply> queryAllApply(){
        return applyService.All();
    }
    //添加
    @RequestMapping("addApply.action")
    public boolean addApply(Apply apply){
        QueryWrapper<File> queryWrapper = new QueryWrapper<File>();
        File file = fileService.getOne(queryWrapper);
        String longId = applyMapper.getLongId();
        String date = IDUtil.getApplyId(longId);
        apply.setApplyId(date);
        apply.setProductId(file.getProductId());
        apply.setProductName(file.getProductName());
        apply.setCheckTag("0");
        apply.setManufactureTag("0");
        return applyService.save(apply);
    }
    //查询未审核的生产计划
    @RequestMapping("queryByCheckTag.action")
    public List<Apply> queryByCheckTag(Apply apply){
        QueryWrapper<Apply> queryWrapper = new QueryWrapper<Apply>();
        if (StringUtils.isEmpty(apply.getCheckTag())){
            queryWrapper.like("check_tag","0");
        }
        return applyService.list(queryWrapper);
    }
    //查询生产计划
    @RequestMapping("queryBy.action")
    public List<Apply> queryBy(Apply apply){
        QueryWrapper<Apply> queryWrapper = new QueryWrapper<Apply>();
        if (!StringUtils.isEmpty(apply.getApplyId())){
            queryWrapper.like("apply_id",apply.getApplyId());
        }
        if (!StringUtils.isEmpty(apply.getProductName())){
            queryWrapper.like("product_name",apply.getProductName());
        }
        if (!StringUtils.isEmpty(apply.getCheckTag())){
            queryWrapper.like("check_tag",apply.getCheckTag());
        }
        return applyService.list(queryWrapper);
    }
    //根据id查询
    @RequestMapping("queryById.action")
    public Apply queryById(int id){
        return applyService.getById(id);
    }
    //根据id修改
    @RequestMapping("updateApply.action")
    public boolean updateApply(Apply apply){
        return applyService.updateById(apply);
    }
}
