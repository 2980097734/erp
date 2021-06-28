package com.guigu.erp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guigu.erp.domain.DesignProcedure;
import org.apache.ibatis.annotations.Select;

public interface DesignProcedureMapper extends BaseMapper<DesignProcedure> {
    @Select("select m.`design_id` from `m_design_procedure` m order by m.`id` desc limit 0,1")
    String getLongId();
}
