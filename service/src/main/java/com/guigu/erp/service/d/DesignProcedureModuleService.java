package com.guigu.erp.service.d;

import com.baomidou.mybatisplus.extension.service.IService;
import com.guigu.erp.domain.DesignProcedureModule;
import com.guigu.erp.domain.ResultUtil;

import java.util.List;

public interface DesignProcedureModuleService extends IService<DesignProcedureModule> {
    ResultUtil saveBatchExtend(List<DesignProcedureModule> designProcedureModules);

    List<DesignProcedureModule> selectByPId(int id);

    ResultUtil updateBatchExtend(List<DesignProcedureModule> designProcedureModules);

    ResultUtil updateBatchExtendById(List<DesignProcedureModule> designProcedureModules);

    boolean updateByPId(int id);

    boolean updateChangeByPId(int id);
}
