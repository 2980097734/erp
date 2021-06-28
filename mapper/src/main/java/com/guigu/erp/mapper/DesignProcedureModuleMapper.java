package com.guigu.erp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guigu.erp.domain.DesignProcedureModule;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DesignProcedureModuleMapper extends BaseMapper<DesignProcedureModule> {
    @Select("select dpm.* from `m_design_procedure_module`dpm left join \n" +
            "`m_design_procedure_details`dpd on dpm.`parent_id`= dpd.`id`\n" +
            "left join `m_design_procedure` dp \n" +
            "on dpd.`parent_id` = dp.`id` where dp.`product_id` =#{designId}\n" +
            "and dpm.`product_id` = #{productId}")
    List<DesignProcedureModule> selectById(@Param("designId") String designId, @Param("productId") String productId);
}
