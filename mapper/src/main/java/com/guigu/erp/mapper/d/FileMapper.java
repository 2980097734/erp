package com.guigu.erp.mapper.d;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guigu.erp.domain.d.File;
import org.apache.ibatis.annotations.Select;

public interface FileMapper extends BaseMapper<File> {
    @Select("select f.* from `d_file` f left join `m_design_procedure` dp \n" +
            "on f.`product_id` = dp.`product_id`\n" +
            "where f.`product_id` = #{productId} and dp.design_module_tag =2")
    File selectByProductId(String productId);
}
