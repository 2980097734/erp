package com.guigu.erp.service.m.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guigu.erp.domain.m.Apply;
import com.guigu.erp.mapper.m.ApplyMapper;
import com.guigu.erp.service.m.ApplyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplyServiceImpl extends ServiceImpl<ApplyMapper, Apply> implements ApplyService {
    @Override
    public List<Apply> All() {
        QueryWrapper<Apply> queryWrapper = new QueryWrapper<Apply>();
        List<Apply> applies = this.list(queryWrapper);
        return applies;
    }
}
