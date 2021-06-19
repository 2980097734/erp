package com.guigu.erp.service.d.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guigu.erp.domain.IDUtil;
import com.guigu.erp.domain.d.File;
import com.guigu.erp.domain.d.Module;
import com.guigu.erp.domain.d.ModuleDetails;
import com.guigu.erp.mapper.d.ModuleDetailsMapper;
import com.guigu.erp.mapper.d.ModuleMapper;
import com.guigu.erp.service.d.FileService;
import com.guigu.erp.service.d.ModuleDetailsService;
import com.guigu.erp.service.d.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ModuleDetailsServiceImpl extends ServiceImpl<ModuleDetailsMapper, ModuleDetails> implements ModuleDetailsService {
    @Autowired
    private FileService fileService;

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private ModuleMapper moduleMapper;
    @Override
    @Transactional
    public void addmodule(List<ModuleDetails> list) {
        QueryWrapper<File> fileQueryWrapper = new QueryWrapper<File>();
        ModuleDetails getone =  list.get(0);
        fileQueryWrapper.eq("PRODUCT_ID",getone.getGoodsId());
        File file = fileService.getOne(fileQueryWrapper);
        Module module = new Module();
        module.setProductId(getone.getGoodsId());
        module.setProductName(file.getProductName());
        module.setFirstKindId(file.getFirstKindId());
        module.setFirstKindName(file.getFirstKindName());
        module.setSecondKindId(file.getSecondKindId());
        module.setSecondKindName(file.getSecondKindName());
        module.setThirdKindId(file.getThirdKindId());
        module.setThirdKindName(file.getThirdKindName());
        module.setDesigner(getone.getDesigner());
        module.setModuleDescribe(getone.getModuleDescribe());
        double xx = 0;
        for (ModuleDetails moduleDetailsDto:list)
            xx+=moduleDetailsDto.getCostPrice();
        module.setCostPriceSum(xx);
        module.setRegister(file.getRegister());
        module.setRegisterTime(file.getRegisterTime());
        module.setCheckTag("S001-0");
        module.setChangeTag("B002-0");
        String longId = moduleMapper.getLongId();
        String designId = IDUtil.getDesignId(longId);
        module.setDesignId(designId);
        System.out.println(module);
        moduleService.save(module);
        int i = moduleService.selectMax();
        int x =1;
        for (ModuleDetails moduleDetailsDto:list) {
            moduleDetailsDto.setParentId(i);
            moduleDetailsDto.setDetailsNumber(x);
            x++;
        }
        this.saveBatch(list);
        file.setDesignModuleTag("W001-1");
        fileService.updateById(file);
    }
}
