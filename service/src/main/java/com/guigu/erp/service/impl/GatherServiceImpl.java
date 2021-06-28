package com.guigu.erp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guigu.erp.domain.Gather;
import com.guigu.erp.mapper.GatherMapper;
import com.guigu.erp.service.GatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GatherServiceImpl extends ServiceImpl<GatherMapper, Gather> implements GatherService {

    @Autowired
    GatherMapper gatherMapper;
    @Override
    public int updates(Gather gather) {
        Gather selectById =gatherMapper.selectById(gather.getId());
        selectById.setChecker(gather.getChecker());
        selectById.setCheckTime(gather.getCheckTime());
        selectById.setCheckTag(gather.getCheckTag());
        boolean b = this.updateById(selectById);
        return 0;
    }
}
