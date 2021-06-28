package com.guigu.erp.mapper.m;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guigu.erp.domain.m.Apply;
import org.apache.ibatis.annotations.Select;

public interface ApplyMapper extends BaseMapper<Apply>{
    @Select("select max(apply_id) from m_apply")
    String getLongId();
}
