package com.guigu.erp.service.d.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guigu.erp.domain.DesignProcedureModule;
import com.guigu.erp.domain.IDUtil;
import com.guigu.erp.domain.ResultUtil;
import com.guigu.erp.domain.d.File;
import com.guigu.erp.domain.d.Module;
import com.guigu.erp.domain.d.ModuleDetails;
import com.guigu.erp.mapper.DesignProcedureModuleMapper;
import com.guigu.erp.mapper.d.ModuleDetailsMapper;
import com.guigu.erp.mapper.d.ModuleMapper;
import com.guigu.erp.service.d.FileService;
import com.guigu.erp.service.d.ModuleDetailsService;
import com.guigu.erp.service.d.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ModuleDetailsServiceImpl extends ServiceImpl<ModuleDetailsMapper, ModuleDetails> implements ModuleDetailsService {
    @Autowired
    private FileService fileService;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private ModuleDetailsMapper moduleDetailsMapper;
    @Autowired
    private ModuleMapper moduleMapper;
    @Autowired
    private DesignProcedureModuleMapper designProcedureModuleMapper;
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
        module.setRegister(getone.getRegister());
        module.setRegisterTime(getone.getRegisterTime());
        module.setCheckTag("0");
        module.setChangeTag("0");
        String longId = moduleMapper.getLongId();
        String designId = IDUtil.getDesignId(longId);
        module.setDesignId(designId);
        moduleService.save(module);
        int i = moduleService.selectMax();
        int x =1;
        for (ModuleDetails moduleDetailsDto:list) {
            moduleDetailsDto.setResidualAmount(moduleDetailsDto.getAmount());
            moduleDetailsDto.setParentId(i);
            moduleDetailsDto.setDetailsNumber(x);
            x++;
        }
        this.saveBatch(list);
        file.setDesignModuleTag("1");
        fileService.updateById(file);
    }

    @Override
    public void addModuleDetails(List<ModuleDetails> list) {
        double xx = 0;
        for (ModuleDetails moduleDetailsDto:list)
            xx+=moduleDetailsDto.getSubtotal();
        Integer id = list.get(0).getParentId();
        Module byId = moduleService.getById(id);
        byId.setCostPriceSum(xx);
        moduleService.updateById(byId);
        int x =1;
        for (ModuleDetails moduleDetailsDto:list) {
            moduleDetailsDto.setDetailsNumber(x);
            x++;
        }
        this.saveOrUpdateBatch(list);
    }

    @Override
    public ResultUtil deleteById(int id, int parentId) {
        ResultUtil<Object> resultUtil = new ResultUtil<Object>();
        Module module = moduleService.getById(parentId);
        ModuleDetails details = this.getById(id);
        List<DesignProcedureModule> designProcedureModules = designProcedureModuleMapper.selectById(module.getProductId(),details.getProductId());
        if (designProcedureModules.size()>0){
            resultUtil.setResult(false);
            resultUtil.setMessage("操作失败，物料已存在产品工序物料明细");
            return resultUtil;
        }
        //删除
        boolean result1 = this.removeById(id);
        QueryWrapper<ModuleDetails> queryWrapper = new QueryWrapper<ModuleDetails>();
        queryWrapper.eq("parent_id",parentId);
        List<ModuleDetails> list = this.list(queryWrapper);
        int costPriceSum=0;
        for (ModuleDetails m:list) {
            float i =(m.getCostPrice().floatValue()) * m.getAmount();
            costPriceSum+=i;
        }
        //物料总成本
        module.setCostPriceSum((double) costPriceSum);
        module.setCheckTag("0");
        boolean result2 = moduleService.updateById(module);
        if (result1==true && result2==true ){
            resultUtil.setResult(true);
            resultUtil.setMessage("操作成功");
            return resultUtil;
        }else {
            resultUtil.setResult(false);
            resultUtil.setMessage("操作失败");
            return resultUtil;
        }
    }

    @Override
    public List<ModuleDetails> selectByProductId(String productId) {
        return moduleDetailsMapper.selectByProductId(productId);
    }
}

