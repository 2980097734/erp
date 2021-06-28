package com.guigu.erp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guigu.erp.domain.GatherDetailsDto;
import com.guigu.erp.mapper.GathDtoMapper;
import com.guigu.erp.service.GathDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GathDtoServiceImpl
        extends ServiceImpl<GathDtoMapper, GatherDetailsDto>
        implements GathDtoService{

    @Autowired
    GathDtoMapper gathDtoMapper;
    @Override
    public List<GatherDetailsDto> gathShenhe(int id) {
        List<GatherDetailsDto> query=gathDtoMapper.query(id);
        return query;
    }
}
