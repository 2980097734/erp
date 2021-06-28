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
import com.guigu.erp.service.d.FileService;
import com.guigu.erp.service.d.ModuleDetailsService;
import com.guigu.erp.service.d.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("ProductMaterial")
public class ProductMaterialController {
    @Autowired
    private FileService fileService;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private ModuleDetailsService moduleDetailsService;
    @Autowired
    private ConfigFileKindService configFileKindService;

    @RequestMapping("queryAllFile1")
    public IPage<File> queryAllFile(@RequestParam(value = "pageno",defaultValue = "1") int pageno,
                                    @RequestParam(value = "pagesize",defaultValue = "10") int pagesize,
                                    File file, Date startTime, Date endTime){
        QueryWrapper<File> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(file.getFirstKindId()))
            queryWrapper.eq("first_kind_id",file.getFirstKindId());
        if (!StringUtils.isEmpty(file.getSecondKindId()))
            queryWrapper.eq("second_kind_id",file.getSecondKindId());
        if (!StringUtils.isEmpty(file.getThirdKindId()))
            queryWrapper.eq("third_kind_id",file.getThirdKindId());
        if (startTime!=null && endTime!=null)
            queryWrapper.between("register_time",startTime,endTime);
        if (startTime!=null && endTime==null)
            queryWrapper.ge("register_time",startTime);
        if (startTime==null && endTime!=null)
            queryWrapper.le("register_time",endTime);
        queryWrapper.eq("type","Y001-1");
        queryWrapper.eq("DESIGN_MODULE_TAG","0");
        return fileService.page(new Page<File>(pageno,pagesize),queryWrapper);
    }

    @RequestMapping("addFile")
    public boolean addFile(File file){
        return fileService.save(file);
    }

    @RequestMapping("editFile")
    public boolean editFile(File file){
        return fileService.updateById(file);
    }

    @RequestMapping("delFile")
    public boolean delFile(int id){
        return fileService.removeById(id);
    }

    @RequestMapping("queryById")
    public File queryById(int id){
        return fileService.getById(id);
    }

    @RequestMapping("queryAllFile2")
    public List<File> queryAllModule(File file){
        QueryWrapper<File> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(file.getProductName()))
            queryWrapper.eq("PRODUCT_NAME",file.getProductName());
        queryWrapper.eq("type","Y001-2");
        return fileService.list(queryWrapper);
    }
    @RequestMapping("addModule")
    public boolean addModule(@RequestBody List<ModuleDetails> moduleDetails){
//        moduleService.save(module);
//        int i = moduleService.selectMax();
//        for (ModuleDetails md:moduleDetails)
//            md.setParentId(i);
        moduleDetailsService.addmodule(moduleDetails);
        return true;
    }
    @RequestMapping("queryModule")
    public Module queryModule(String productId){
        QueryWrapper<Module> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("productId",productId);
        return moduleService.getOne(queryWrapper);
    }

    @RequestMapping("queryAllCon")
    public List<ConfigFileKind> queryAllCon(@RequestParam(value = "pId",defaultValue = "0") Integer pId){
        QueryWrapper<ConfigFileKind> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("p_id",pId);
        return configFileKindService.list(queryWrapper);
    }

    @RequestMapping("queryUsers")
    public String queryUsers(HttpSession session){
        return (String) session.getAttribute("user");
    }

    @RequestMapping("queryAllModule1")
    public IPage<Module> queryAllModule1(@RequestParam(value = "pageno",defaultValue = "1") int pageno,
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
        queryWrapper.eq("check_tag","1");
        return moduleService.page(new Page<Module>(pageno,pagesize),queryWrapper);
    }
    @RequestMapping("updateModule")
    public void updateModule(Module module){
        module.setCheckTag("0");
        moduleService.updateById(module);
    }

    @RequestMapping("addModuleDetails")
    public void addModuleDetails(@RequestBody List<ModuleDetails> list){
            moduleDetailsService.addModuleDetails(list);
    }

    @RequestMapping("/list")
    public List<ConfigFileKind> list(){
        return configFileKindService.list();
    }
}
