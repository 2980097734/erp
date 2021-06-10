package com.guigu.erp.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.guigu.erp.domain.sys.MenuInfo;

import java.util.List;

public interface MenuInfoService extends IService<MenuInfo> {

    public List<MenuInfo> queryAllmenu();
}
