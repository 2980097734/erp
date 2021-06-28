package com.guigu.erp.service.d;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.guigu.erp.domain.DesignProcedure;
import com.guigu.erp.domain.DesignProcedureUtil;

public interface DesignProcedureService extends IService<DesignProcedure> {
    PageInfo<DesignProcedure> queryPage(int pageNo, int pageSize, DesignProcedure designProcedure);

    DesignProcedureUtil designProcedureDetailsByPId(int id);

    boolean designModuleTag(DesignProcedure designProcedure);


    boolean delete(int id);

    boolean checkDesignModuleTag(int id);

    boolean checkTag(DesignProcedure designProcedure);
}
