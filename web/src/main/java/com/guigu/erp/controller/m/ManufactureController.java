package com.guigu.erp.controller.m;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guigu.erp.domain.m.Apply;
import com.guigu.erp.domain.m.Manufacture;
import com.guigu.erp.service.m.ApplyService;
import com.guigu.erp.service.m.ManufactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@RestController
@CrossOrigin
public class ManufactureController {
    @Autowired
    ManufactureService manufactureService;
    @Autowired
    ApplyService applyService;

    //查询审核通过且为派工的生产计划
    @RequestMapping("queryByManufactureTag.action")
    public List<Apply> queryByManufactureTag(Apply apply){
        QueryWrapper<Apply> queryWrapper = new QueryWrapper<Apply>();
        if (StringUtils.isEmpty(apply.getCheckTag())){
            queryWrapper.like("check_tag","s001-1");
        }
        if (StringUtils.isEmpty(apply.getManufactureTag())){
            queryWrapper.like("manufacture_tag","p001-0");
        }
        return applyService.list(queryWrapper);
    }

    @RequestMapping("updateById")
    public boolean updateById(Manufacture manufacture){
        return manufactureService.save(manufacture);
    }
}
