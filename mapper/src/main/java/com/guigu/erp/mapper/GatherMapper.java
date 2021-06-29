package com.guigu.erp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guigu.erp.domain.Gather;
import org.apache.ibatis.annotations.Select;

public interface GatherMapper extends BaseMapper<Gather> {
    @Select("select m.gather_id from s_gather m ORDER by m.id desc limit 0,1")
    String LongId();
}
