package com.guigu.erp.service.d.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guigu.erp.domain.DesignProcedure;
import com.guigu.erp.domain.DesignProcedureDetails;
import com.guigu.erp.domain.DesignProcedureModule;
import com.guigu.erp.domain.ResultUtil;
import com.guigu.erp.domain.d.ModuleDetails;
import com.guigu.erp.mapper.DesignProcedureDetailsMapper;
import com.guigu.erp.mapper.DesignProcedureMapper;
import com.guigu.erp.mapper.DesignProcedureModuleMapper;
import com.guigu.erp.mapper.d.ModuleDetailsMapper;
import com.guigu.erp.service.d.DesignProcedureModuleService;
import com.guigu.erp.service.d.ModuleDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DesignProcedureModuleServiceImpl extends ServiceImpl<DesignProcedureModuleMapper, DesignProcedureModule> implements DesignProcedureModuleService {
    @Autowired
    private DesignProcedureDetailsMapper designProcedureDetailsMapper;
    @Autowired
    private DesignProcedureMapper designProcedureMapper;
    @Autowired
    private ModuleDetailsMapper moduleDetailsMapper;
    @Autowired
    private DesignProcedureModuleMapper designProcedureModuleMapper;
    @Autowired
    private ModuleDetailsService moduleDetailsService;
    //添加 产品生产工序物料明细
    @Transactional
    @Override
    public ResultUtil saveBatchExtend(List<DesignProcedureModule> designProcedureModules) {
        //产品生产工序明细
        DesignProcedureDetails designProcedureDetails = designProcedureDetailsMapper.selectById(designProcedureModules.get(0).getParentId());
        //当前工序物料标志
        designProcedureDetails.setDesignModuleTag("1");
        //工序物料序号details_number+1
        int j = 1;
        int result = 0;
        float moduleSubtotal = 0;
        for (DesignProcedureModule designProcedureModule: designProcedureModules){
            ModuleDetails moduleDetails =moduleDetailsMapper.selectById(designProcedureModule.getId());
            int i =moduleDetails.getResidualAmount()-((designProcedureModule.getAmount()).intValue());
            moduleDetails.setResidualAmount(Integer.valueOf(i));
            //可用数量
            result = moduleDetailsMapper.updateById(moduleDetails);
            designProcedureModule.setDetailsNumber(j);
            //计算当前工序物料成本小计 工序明细
            moduleSubtotal += (designProcedureModule.getSubtotal()).floatValue();
            j++;
        }
        designProcedureDetails.setModuleSubtotal(moduleSubtotal);
        //工序明细 修改当前工序物料标志  计算当前工序物料成本小计
        int result1 = designProcedureDetailsMapper.updateById(designProcedureDetails);

        boolean result3 = this.saveBatch(designProcedureModules);
        ResultUtil<Object> resultUtil = new ResultUtil<Object>();
        if (result1==1 && result3==true){
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
    public List<DesignProcedureModule> selectByPId(int id) {
        QueryWrapper<DesignProcedureModule> wrapper = new QueryWrapper<DesignProcedureModule>();
        wrapper.eq("parent_id",id);
        return this.list(wrapper);
    }

    @Override
    public ResultUtil updateBatchExtend(List<DesignProcedureModule> designProcedureModules) {
        ResultUtil<Object> resultUtil = new ResultUtil<Object>();
        boolean result = this.updateBatchById(designProcedureModules);
        if (result ==true){
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
    public ResultUtil updateBatchExtendById(List<DesignProcedureModule> designProcedureModules) {

        DesignProcedureDetails designProcedureDetails = designProcedureDetailsMapper.selectById(designProcedureModules.get(0).getParentId());
        designProcedureDetails.setDesignModuleChangeTag("1");
        int result1 = designProcedureDetailsMapper.updateById(designProcedureDetails);
        //design_module_change_tag   design_procedure
        DesignProcedure designProcedure = designProcedureMapper.selectById(designProcedureDetails.getParentId());
        designProcedure.setDesignModuleChangeTag("1");
        int result2 = designProcedureMapper.updateById(designProcedure);

        boolean result3 = this.updateBatchById(designProcedureModules);
        ResultUtil<Object> resultUtil = new ResultUtil<Object>();
        if (result1 >0 && result2>0 && result3==true){
            resultUtil.setResult(true);
            resultUtil.setMessage("操作成功");
            return resultUtil;
        }else {
            resultUtil.setResult(false);
            resultUtil.setMessage("操作失败");
            return resultUtil;
        }
    }

    @Transactional
    @Override
    public boolean updateByPId(int parentId) {
        //当前parent_id 的所有工序物料明细
        QueryWrapper<DesignProcedureModule> designProcedureModuleQueryWrapper = new QueryWrapper<DesignProcedureModule>();
        designProcedureModuleQueryWrapper.eq("parent_id",parentId);
        List<DesignProcedureModule> list = this.list(designProcedureModuleQueryWrapper);

        int result1 = 0;
        for (DesignProcedureModule dpm :list){
            List<ModuleDetails> moduleDetails = moduleDetailsMapper.selectByPid(parentId, dpm.getId());
            //residual_amount
            for (ModuleDetails md :moduleDetails ){
                if (md.getProductId().equals(dpm.getProductId())){
                    md.setResidualAmount(md.getResidualAmount()+(dpm.getAmount()).intValue());
                    continue;
                }
            }
            boolean result = moduleDetailsService.updateBatchById(moduleDetails);
            result1++;
        }

        //删除
        boolean remove = this.remove(designProcedureModuleQueryWrapper);


        //当前工序物料变更标志design_module_change_tag module_subtotal  design_module_tag
        DesignProcedureDetails designProcedureDetails = designProcedureDetailsMapper.selectById(parentId);
        designProcedureDetails.setDesignModuleTag("1");
        /*designProcedureDetails.setDesignModuleChangeTag("0");*/
        designProcedureDetails.setModuleSubtotal(0f);
        int result2 = designProcedureDetailsMapper.updateById(designProcedureDetails);

        if (result1==list.size() && result2 >0 && remove==true)
            return true;
        return false;
    }

    @Override
    public boolean updateChangeByPId(int parentId) {
        //当前parent_id 的所有工序物料明细
        QueryWrapper<DesignProcedureModule> designProcedureModuleQueryWrapper = new QueryWrapper<DesignProcedureModule>();
        designProcedureModuleQueryWrapper.eq("parent_id",parentId);
        List<DesignProcedureModule> list = this.list(designProcedureModuleQueryWrapper);

        int result1 = 0;
        boolean remove = true;
        if (list.size()>0) {
            for (DesignProcedureModule dpm : list) {
                List<ModuleDetails> moduleDetails = moduleDetailsMapper.selectByPid(parentId, dpm.getId());
                //residual_amount
                for (ModuleDetails md : moduleDetails) {
                    if (md.getProductId().equals(dpm.getProductId())) {
                        md.setResidualAmount(md.getResidualAmount() + (dpm.getAmount()).intValue());
                        continue;
                    }
                }
                boolean result = moduleDetailsService.updateBatchById(moduleDetails);
                result1++;
            }

            //删除
            remove = this.remove(designProcedureModuleQueryWrapper);
        }

        //当前工序物料变更标志design_module_change_tag module_subtotal  design_module_tag
        DesignProcedureDetails designProcedureDetails = designProcedureDetailsMapper.selectById(parentId);
        /*designProcedureDetails.setDesignModuleTag("1");*/
        designProcedureDetails.setDesignModuleChangeTag("1");
        designProcedureDetails.setModuleSubtotal(0f);
        int result2 = designProcedureDetailsMapper.updateById(designProcedureDetails);

        DesignProcedure designProcedure = designProcedureMapper.selectById(designProcedureDetails.getParentId());
        designProcedure.setDesignModuleTag("1");
        int i = designProcedureMapper.updateById(designProcedure);

        if (result1==list.size() && result2 >0 && remove==true && i>0)
            return true;
        return false;
    }
}
