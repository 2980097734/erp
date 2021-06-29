package com.guigu.erp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.guigu.erp.domain.Gather;

public interface GatherService extends IService<Gather> {
    int updates(Gather gather);
}
