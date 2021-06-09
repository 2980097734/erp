package com.guigu.erp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.guigu.erp.domain.MenuInfo;

import java.util.List;

public interface MenuInfoService extends IService<MenuInfo> {

    public List<MenuInfo> queryAllmenu();
}
