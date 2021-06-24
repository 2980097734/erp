package com.guigu.erp.service.d.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guigu.erp.domain.d.ConfigFileKind;
import com.guigu.erp.domain.sys.MenuInfo;
import com.guigu.erp.mapper.d.ConfigFileKindMapper;
import com.guigu.erp.service.d.ConfigFileKindService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfigFileKindServiceImpl extends ServiceImpl<ConfigFileKindMapper, ConfigFileKind> implements ConfigFileKindService {
    @Override
    public List<ConfigFileKind> queryconfig() {
        QueryWrapper<ConfigFileKind> queryWrapper = new QueryWrapper<ConfigFileKind>();
        queryWrapper.eq("p_id",0);
        List<ConfigFileKind> parents = this.list(queryWrapper);

        for(ConfigFileKind p:parents){
            queryWrapper = new QueryWrapper<ConfigFileKind>();
            queryWrapper.eq("p_id",p.getId());
            List<ConfigFileKind> child = this.list(queryWrapper);
            p.setChildConfig(child);
            for(ConfigFileKind p1:child){
                queryWrapper = new QueryWrapper<ConfigFileKind>();
                queryWrapper.eq("p_id",p1.getId());
                List<ConfigFileKind> child1 = this.list(queryWrapper);
                p1.setChildConfig(child1);
            }
        }
        return parents;
    }
}
