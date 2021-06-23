package com.guigu.erp.controller;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guigu.erp.domain.d.ConfigFileKind;
import com.guigu.erp.domain.d.File;
import com.guigu.erp.domain.d.Module;
import com.guigu.erp.domain.d.ModuleDetails;
import com.guigu.erp.service.d.ConfigFileKindService;
import com.guigu.erp.service.d.ModuleDetailsService;
import com.guigu.erp.service.d.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("ProductTwo")
public class ProductTwoController {
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private ModuleDetailsService moduleDetailsService;
    @Autowired
    private ConfigFileKindService configFileKindService;

    @RequestMapping("queryAllModule")
    public IPage<Module> queryAllModule(@RequestParam(value = "pageno",defaultValue = "1") int pageno,
                                      @RequestParam(value = "pagesize",defaultValue = "10") int pagesize,
                                      Module module, Date startTime, Date endTime){
        QueryWrapper<Module> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(module.getFirstKindId()))
            queryWrapper.eq("first_kind_id",module.getFirstKindId());
        if (!StringUtils.isEmpty(module.getSecondKindId()))
            queryWrapper.eq("second_kind_id",module.getSecondKindId());
        if (!StringUtils.isEmpty(module.getThirdKindId()))
            queryWrapper.eq("third_kind_id",module.getThirdKindId());
        if (startTime!=null && endTime!=null)
            queryWrapper.between("register_time",startTime,endTime);
        if (startTime!=null && endTime==null)
            queryWrapper.ge("register_time",startTime);
        if (startTime==null && endTime!=null)
            queryWrapper.le("register_time",endTime);
        queryWrapper.eq("check_tag","S001-0");
        return moduleService.page(new Page<Module>(pageno,pagesize),queryWrapper);
    }

    @RequestMapping("queryAllCon")
    public List<ConfigFileKind> queryAllCon(@RequestParam(value = "pId",defaultValue = "0") Integer pId){
        QueryWrapper<ConfigFileKind> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("p_id",pId);
        return configFileKindService.list(queryWrapper);
    }

    @RequestMapping("queryOneModule")
    public Module queryOneModule(int id){
            return moduleService.getById(id);
    }

    @RequestMapping("queryByModule")
    public List<ModuleDetails> queryByModule(int id){
        QueryWrapper<ModuleDetails> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",id);
        return moduleDetailsService.list(queryWrapper);
    }

    @RequestMapping("moduleFh")
    public boolean moduleFh(Module module){
        return moduleService.updateById(module);
    }

    @RequestMapping("queryAllModule2")
    public IPage<Module> queryAllModule2(@RequestParam(value = "pageno",defaultValue = "1") int pageno,
                                        @RequestParam(value = "pagesize",defaultValue = "10") int pagesize,
                                        Module module, Date startTime, Date endTime){
        QueryWrapper<Module> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(module.getFirstKindId()))
            queryWrapper.eq("first_kind_id",module.getFirstKindId());
        if (!StringUtils.isEmpty(module.getSecondKindId()))
            queryWrapper.eq("second_kind_id",module.getSecondKindId());
        if (!StringUtils.isEmpty(module.getThirdKindId()))
            queryWrapper.eq("third_kind_id",module.getThirdKindId());
        if (startTime!=null && endTime!=null)
            queryWrapper.between("register_time",startTime,endTime);
        if (startTime!=null && endTime==null)
            queryWrapper.ge("register_time",startTime);
        if (startTime==null && endTime!=null)
            queryWrapper.le("register_time",endTime);
        return moduleService.page(new Page<Module>(pageno,pagesize),queryWrapper);
    }
}
