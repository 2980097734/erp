package com.guigu.erp.service.d.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guigu.erp.domain.d.Module;
import com.guigu.erp.mapper.d.ModuleMapper;
import com.guigu.erp.service.d.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModuleServiceImpl extends ServiceImpl<ModuleMapper, Module> implements ModuleService {
    @Autowired
    private ModuleMapper moduleMapper;
    @Override
    public int selectMax() {
        return moduleMapper.selectMax();
    }
}
