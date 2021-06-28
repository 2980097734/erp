package com.guigu.erp.mapper.d;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guigu.erp.domain.d.ModuleDetails;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ModuleDetailsMapper extends BaseMapper<ModuleDetails> {

    @Select("select md.* from `d_module_details` md left join `d_module` m\n" +
            "on md.`parent_id` = m.`id` left join `d_file` f on f.`product_id` = m.`product_id`\n" +
            "where f.`product_id` = #{productId}")
    List<ModuleDetails> selectByProductId(String productId);

    @Select("select distinct md.* from `d_module_details` md left join `d_module` d\n" +
            "on md.`parent_id` = d.`id`  left join `m_design_procedure` dp\n" +
            "on d.`product_id` = dp.`product_id` left join `m_design_procedure_details` dpd\n" +
            "on dpd.`parent_id` = dp.`id` left join `m_design_procedure_module` dpm \n" +
            "on dpm.`parent_id` = dpd.`id` where dpm.`parent_id` = #{parentId}  and dpm.`id` = #{id}")
    List<ModuleDetails> selectByPid(@Param("parentId")int parentId , @Param("id") int id);

}
