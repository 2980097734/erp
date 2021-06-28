package com.guigu.erp.service.d.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guigu.erp.domain.*;
import com.guigu.erp.domain.d.File;
import com.guigu.erp.mapper.DesignProcedureDetailsMapper;
import com.guigu.erp.mapper.DesignProcedureMapper;
import com.guigu.erp.mapper.DesignProcedureModuleMapper;
import com.guigu.erp.mapper.d.FileMapper;
import com.guigu.erp.service.d.DesignProcedureDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DesignProcedureDetailsServiceImpl extends ServiceImpl<DesignProcedureDetailsMapper, DesignProcedureDetails> implements DesignProcedureDetailsService {
    @Autowired
    private DesignProcedureMapper designProcedureMapper;
    @Autowired
    private FileMapper fileMapper;
    @Autowired
    private DesignProcedureModuleMapper designProcedureModuleMapper;

    @Override
    public List<DesignProcedureDetails> selectByPid(String productId) {
        QueryWrapper<DesignProcedure> designProcedureQueryWrapper = new QueryWrapper<DesignProcedure>();
        designProcedureQueryWrapper.eq("product_id", productId);
        DesignProcedure designProcedure = designProcedureMapper.selectOne(designProcedureQueryWrapper);
        QueryWrapper<DesignProcedureDetails> wrapper = new QueryWrapper<DesignProcedureDetails>();
        wrapper.eq("parent_id", designProcedure.getId());
        return this.list(wrapper);
    }

    @Transactional
    @Override
    public ResultUtil saveBatchExtend(List<DesignProcedureDetails> designProcedureDetails) {
        QueryWrapper<File> fileQueryWrapper = new QueryWrapper<File>();
        fileQueryWrapper.eq("product_id", designProcedureDetails.get(0).getGoodsId());
        File file = fileMapper.selectOne(fileQueryWrapper);
        //design_procedure_tag
        file.setDesignProcedureTag("1");
        //工序组成标志
        int result1 = fileMapper.updateById(file);

        //工序设计单编号	201+当前日期+4位流水号
        String longId = designProcedureMapper.getLongId();
        String designProcedureId = IDUtil.getDesignProcedureId(longId);

        DesignProcedure designProcedure = new DesignProcedure();
        designProcedure.setDesignId(designProcedureId);
        designProcedure.setFirstKindId(file.getFirstKindId());
        designProcedure.setFirstKindName(file.getFirstKindName());
        designProcedure.setSecondKindId(file.getSecondKindId());
        designProcedure.setSecondKindName(file.getSecondKindName());
        designProcedure.setThirdKindId(file.getThirdKindId());
        designProcedure.setThirdKindName(file.getThirdKindName());
        designProcedure.setProductId(file.getProductId());
        designProcedure.setProductName(file.getProductName());
        designProcedure.setProcedureDescribe(designProcedureDetails.get(0).getDesignProcedureDescribe());
        float costPriceSum = 0;
        for (DesignProcedureDetails dpd : designProcedureDetails) {
            costPriceSum += dpd.getSubtotal();
        }
        designProcedure.setCostPriceSum(BigDecimal.valueOf(costPriceSum));
        designProcedure.setDesigner(designProcedureDetails.get(0).getRegister());
        designProcedure.setRegister(file.getRegister());
        designProcedure.setRegisterTime(file.getRegisterTime());
        designProcedure.setCheckTag("0");
        designProcedure.setDesignModuleTag("0");
        designProcedure.setDesignModuleChangeTag("0");
        designProcedure.setChangeTag("0");
        //产品生产工序(m_design_procedure)
        int result2 = designProcedureMapper.insert(designProcedure);

        int detailsNumber = 1;
        for (DesignProcedureDetails dpd : designProcedureDetails) {
            dpd.setParentId(designProcedure.getId());
            dpd.setDetailsNumber(detailsNumber);
            detailsNumber++;
        }
        //产品生产工序明细(m_design_procedure_details)
        boolean result3 = this.saveBatch(designProcedureDetails);

        ResultUtil<Object> resultUtil = new ResultUtil<Object>();
        if (result1 > 0 && result2 > 0 && result3 == true) {
            resultUtil.setResult(true);
            resultUtil.setMessage("操作成功");
            return resultUtil;
        } else {
            resultUtil.setResult(false);
            resultUtil.setMessage("操作失败");
            return resultUtil;
        }
    }

    @Override
    public ResultUtil delete(int id, int parentId) {
        ResultUtil<Object> resultUtil = new ResultUtil<Object>();
        QueryWrapper<DesignProcedureModule> designProcedureModuleQueryWrapper = new QueryWrapper<DesignProcedureModule>();
        designProcedureModuleQueryWrapper.eq("parent_id",id);
        List<DesignProcedureModule> list = designProcedureModuleMapper.selectList(designProcedureModuleQueryWrapper);
        if (list.size()>0){
            resultUtil.setResult(false);
            resultUtil.setMessage("操作失败");
            return resultUtil;
        }
        DesignProcedure designProcedure = designProcedureMapper.selectById(parentId);
        designProcedure.setCheckTag("0");
        int result1 = designProcedureMapper.updateById(designProcedure);
        boolean result2 = this.removeById(id);
        if (result1 > 0 && result2 == true) {
            resultUtil.setResult(true);
            resultUtil.setMessage("操作成功");
            return resultUtil;
        } else {
            resultUtil.setResult(false);
            resultUtil.setMessage("操作失败");
            return resultUtil;
        }
    }

    @Override
    public ResultUtil saveOrUpdateBatchExtend(List<DesignProcedureDetails> designProcedureDetails) {
        //m_design_procedure
        QueryWrapper<DesignProcedure> designProcedureQueryWrapper = new QueryWrapper<DesignProcedure>();
        designProcedureQueryWrapper.eq("design_id",designProcedureDetails.get(0).getGoodsId());

        DesignProcedure designProcedure = designProcedureMapper.selectOne(designProcedureQueryWrapper);
        designProcedure.setCheckTag("0");
        designProcedure.setChangeTag("1");
        designProcedure.setChanger(designProcedureDetails.get(0).getChanger());
        designProcedure.setChangeTime(designProcedureDetails.get(0).getChangeTime());
        designProcedure.setProcedureDescribe(designProcedureDetails.get(0).getDesignProcedureDescribe());

        int result1 = designProcedureMapper.updateById(designProcedure);

        QueryWrapper<DesignProcedureDetails> designProcedureDetailsQueryWrapper = new QueryWrapper<DesignProcedureDetails>();
        designProcedureDetailsQueryWrapper.eq("parent_id",designProcedure.getId());
        boolean result2 = this.remove(designProcedureDetailsQueryWrapper);


        int detailsNumber = 1;
        for (DesignProcedureDetails dpd : designProcedureDetails) {
            dpd.setParentId(designProcedure.getId());
            dpd.setDetailsNumber(detailsNumber);
            detailsNumber++;
        }

        boolean result3 = this.saveBatch(designProcedureDetails);

        ResultUtil<Object> resultUtil = new ResultUtil<Object>();
        if (result1 > 0 && result2 ==true && result3 == true) {
            resultUtil.setResult(true);
            resultUtil.setMessage("操作成功");
            return resultUtil;
        } else {
            resultUtil.setResult(false);
            resultUtil.setMessage("操作失败");
            return resultUtil;
        }
    }
}
