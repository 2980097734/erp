package com.guigu.erp.mapper.d;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guigu.erp.domain.d.Module;
import org.apache.ibatis.annotations.Select;

public interface ModuleMapper extends BaseMapper<Module> {
    int selectMax();
    @Select("select max(DESIGN_ID) from d_module")
    String getLongId();
}
