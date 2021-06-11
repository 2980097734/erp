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

@RestController
@CrossOrigin
public class ApplyController {
    @Autowired
    ApplyService applyService;
    @RequestMapping("queryAllApply.action")
    public IPage<Apply> queryAllApply(@RequestParam(value = "pageno",defaultValue = "1") int pageno,
                                      @RequestParam(value = "pagesize",defaultValue = "5") int pagesize,
                                      Apply apply){
        QueryWrapper<Apply> queryWrapper = new QueryWrapper<Apply>();
        return applyService.page(new Page<Apply>(pageno,pagesize),null);
    }

}
