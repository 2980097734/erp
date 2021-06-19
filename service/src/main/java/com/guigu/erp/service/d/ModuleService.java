package com.guigu.erp.service.d;

import com.baomidou.mybatisplus.extension.service.IService;
import com.guigu.erp.domain.d.Module;

public interface ModuleService extends IService<Module> {
    public int selectMax();

}
