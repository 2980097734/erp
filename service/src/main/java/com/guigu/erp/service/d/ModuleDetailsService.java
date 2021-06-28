package com.guigu.erp.service.d;

import com.baomidou.mybatisplus.extension.service.IService;
import com.guigu.erp.domain.ResultUtil;
import com.guigu.erp.domain.d.ModuleDetails;

import java.util.List;

public interface ModuleDetailsService extends IService<ModuleDetails> {
    public void addmodule(List<ModuleDetails> list);

    public void addModuleDetails(List<ModuleDetails> list);

    ResultUtil deleteById(int id, int parentId);
    List<ModuleDetails> selectByProductId(String productId);
}
