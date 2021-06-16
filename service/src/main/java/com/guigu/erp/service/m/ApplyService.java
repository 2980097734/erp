package com.guigu.erp.service.m;

import com.baomidou.mybatisplus.extension.service.IService;
import com.guigu.erp.domain.m.Apply;

import java.util.List;

public interface ApplyService extends IService<Apply> {
    List<Apply> All();
}
