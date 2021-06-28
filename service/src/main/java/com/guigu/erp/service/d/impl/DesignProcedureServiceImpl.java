package com.guigu.erp.service.d.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guigu.erp.domain.DesignProcedure;
import com.guigu.erp.domain.DesignProcedureDetails;
import com.guigu.erp.domain.DesignProcedureModule;
import com.guigu.erp.domain.DesignProcedureUtil;
import com.guigu.erp.mapper.DesignProcedureMapper;
import com.guigu.erp.mapper.DesignProcedureModuleMapper;
import com.guigu.erp.service.d.DesignProcedureDetailsService;
import com.guigu.erp.service.d.DesignProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DesignProcedureServiceImpl extends ServiceImpl<DesignProcedureMapper, DesignProcedure> implements DesignProcedureService {
    @Autowired
    private DesignProcedureDetailsService designProcedureDetailsService;
    @Autowired
    private DesignProcedureMapper designProcedureMapper;
    @Autowired
    private DesignProcedureModuleMapper designProcedureModuleMapper;
    @Override
    public PageInfo<DesignProcedure> queryPage(int pageNo, int pageSize, DesignProcedure designProcedure) {
        List<DesignProcedure> designProcedureList = null;
        QueryWrapper<DesignProcedure> queryWrapper = new QueryWrapper<DesignProcedure>();
        if (designProcedure != null) {
            if (designProcedure.getProductName() != null && designProcedure.getProductName() != "")
                queryWrapper.like("product_name", designProcedure.getProductName());

            if (designProcedure.getCheckTag() != null && designProcedure.getCheckTag() != "")
                queryWrapper.eq("check_tag", designProcedure.getCheckTag());

            if (designProcedure.getDesignModuleTag() != null && designProcedure.getDesignModuleTag() != "")
                queryWrapper.eq("design_module_tag", designProcedure.getDesignModuleTag());

            // 追加条件 建档时间开始
            if (designProcedure.getRegisterTime() != null)
                queryWrapper.ge("register_time", designProcedure.getRegisterTime());
            // 追加条件 建档时间结束
            if (designProcedure.getRegisterTime2() != null )
                queryWrapper.le("register_time", designProcedure.getRegisterTime2());

            // 追加条件 产品i级分类编号
            if (designProcedure.getFirstKindId() != null && designProcedure.getFirstKindId() != "")
                queryWrapper.eq("first_kind_id", designProcedure.getFirstKindId());
            // 追加条件 产品ii级分类编号
            if (designProcedure.getSecondKindId() != null && designProcedure.getSecondKindId() != "")
                queryWrapper.eq("second_kind_id", designProcedure.getSecondKindId());
            // 追加条件 产品iii级分类编号
            if (designProcedure.getThirdKindId() != null && designProcedure.getThirdKindId() != "")
                queryWrapper.eq("third_kind_id", designProcedure.getThirdKindId());

            PageHelper.startPage(pageNo, pageSize);
            designProcedureList = this.list(queryWrapper);
        } else {
            PageHelper.startPage(pageNo, pageSize);
            designProcedureList = this.list(queryWrapper);
        }
        return new PageInfo<DesignProcedure>(designProcedureList);
    }

    @Override
    public DesignProcedureUtil designProcedureDetailsByPId(int id) {
        DesignProcedure designProcedure = this.getById(id);
        QueryWrapper<DesignProcedureDetails> wrapper = new QueryWrapper<DesignProcedureDetails>();
        wrapper.eq("parent_id",id);
        List<DesignProcedureDetails> procedureDetailsList = designProcedureDetailsService.list(wrapper);

        DesignProcedureUtil designProcedureUtil = new DesignProcedureUtil();
        designProcedureUtil.setDesignProcedure(designProcedure);
        designProcedureUtil.setDesignProcedureDetails(procedureDetailsList);
        return designProcedureUtil;
    }
    //审核通过
    @Override
    public boolean designModuleTag(DesignProcedure designProcedure) {
        DesignProcedure procedure = this.getById(designProcedure.getId());
        procedure.setChecker(designProcedure.getChecker());
        procedure.setCheckTime(designProcedure.getCheckTime());
        procedure.setCheckSuggestion(designProcedure.getCheckSuggestion());
        //1: 审核通过
        procedure.setDesignModuleTag("2");
        int result = designProcedureMapper.updateById(procedure);
        if (result>0)
            return true;
        else
            return false;
    }

    //审核不通过
    @Transactional
    @Override
    public boolean delete(int id) {
        QueryWrapper<DesignProcedureDetails> designProcedureDetailsQueryWrapper = new QueryWrapper<DesignProcedureDetails>();
        designProcedureDetailsQueryWrapper.eq("parent_id",id);
        List<DesignProcedureDetails> list = designProcedureDetailsService.list(designProcedureDetailsQueryWrapper);
        int i = 0;
        for (DesignProcedureDetails dpd:list){
            QueryWrapper<DesignProcedureModule> designProcedureModuleQueryWrapper = new QueryWrapper<DesignProcedureModule>();
            designProcedureModuleQueryWrapper.eq("parent_id",dpd.getId());
            int delete = designProcedureModuleMapper.delete(designProcedureModuleQueryWrapper);
            i++;
        }
        boolean result = designProcedureDetailsService.remove(designProcedureDetailsQueryWrapper);
        //design_module_tag
        DesignProcedure procedure = this.getById(id);
        procedure.setDesignModuleTag("0");
        boolean result2 = this.updateById(procedure);

        if (i==list.size() && result ==true &&result2==true)
            return  true;
        return false;
    }

    @Override
    public boolean checkDesignModuleTag(int id) {
        DesignProcedure designProcedure = this.getById(id);
        /*工序物料设计标志 g002-0: 未设计 g002-1: 已提交 g002-2: 已审核*/
        designProcedure.setDesignModuleTag("1");
        return this.updateById(designProcedure);
    }

    //工序审核
    @Override
    public boolean checkTag(DesignProcedure designProcedure) {
        DesignProcedure procedure = this.getById(designProcedure.getId());
        procedure.setChecker(designProcedure.getChecker());
        procedure.setCheckTime(designProcedure.getCheckTime());
        procedure.setCheckSuggestion(designProcedure.getCheckSuggestion());
        procedure.setCheckTag(designProcedure.getCheckTag());
        int result = designProcedureMapper.updateById(procedure);
        if (result>0)
            return true;
        else
            return false;
    }


}
