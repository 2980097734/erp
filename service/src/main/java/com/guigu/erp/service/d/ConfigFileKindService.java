package com.guigu.erp.service.d;

import com.baomidou.mybatisplus.extension.service.IService;
import com.guigu.erp.domain.d.ConfigFileKind;

import java.util.List;

public interface ConfigFileKindService extends IService<ConfigFileKind> {

    public List<ConfigFileKind> queryconfig();
}
