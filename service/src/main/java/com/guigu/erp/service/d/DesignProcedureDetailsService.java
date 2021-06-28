package com.guigu.erp.service.d;

import com.baomidou.mybatisplus.extension.service.IService;
import com.guigu.erp.domain.DesignProcedureDetails;
import com.guigu.erp.domain.ResultUtil;

import java.util.List;

public interface DesignProcedureDetailsService extends IService<DesignProcedureDetails> {
    List<DesignProcedureDetails> selectByPid(String productId);

    ResultUtil saveBatchExtend(List<DesignProcedureDetails> designProcedureDetails);

    ResultUtil delete(int id, int parentId);

    ResultUtil saveOrUpdateBatchExtend(List<DesignProcedureDetails> designProcedureDetails);
}
