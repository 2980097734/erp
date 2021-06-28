package com.guigu.erp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guigu.erp.domain.GatherDetailsDto;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GathDtoMapper extends BaseMapper<GatherDetailsDto> {
    @Select("select * from s_gather ga " +
            "left join s_gather_details de " +
            "on ga.id=de.parent_id where de.parent_id=#{id}")
    List<GatherDetailsDto> query(int id);
}
