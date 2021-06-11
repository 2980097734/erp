package com.guigu.erp.service.impl.sys;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guigu.erp.mapper.sys.MenuInfoMapper;
import com.guigu.erp.domain.sys.MenuInfo;
import com.guigu.erp.service.sys.MenuInfoService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MenuInfoServiceImpl extends ServiceImpl<MenuInfoMapper, MenuInfo> implements MenuInfoService {

    public List<MenuInfo> queryAllmenu() {
        QueryWrapper<MenuInfo> queryWrapper = new QueryWrapper<MenuInfo>();
        queryWrapper.eq("parent_id",0);
        List<MenuInfo> parents = this.list(queryWrapper);


        for (MenuInfo p:parents){
            queryWrapper = new QueryWrapper<MenuInfo>();
            queryWrapper.eq("parent_id",p.getId());
            List<MenuInfo> child = this.list(queryWrapper);
            p.setChildMenu(child);
            for (MenuInfo p1:child){
                queryWrapper = new QueryWrapper<MenuInfo>();
                queryWrapper.eq("parent_id",p1.getId());
                List<MenuInfo> child1 = this.list(queryWrapper);
                p1.setChildMenu(child1);
            }
        }
        return parents;
    }
}
