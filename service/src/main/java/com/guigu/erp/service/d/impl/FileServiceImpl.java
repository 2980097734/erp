package com.guigu.erp.service.d.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guigu.erp.domain.d.ConfigFileKind;
import com.guigu.erp.domain.d.File;
import com.guigu.erp.mapper.d.FileMapper;
import com.guigu.erp.service.d.ConfigFileKindService;
import com.guigu.erp.service.d.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.Date;
import java.util.List;

@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {

    @Autowired
    ConfigFileKindService configFileKindService;

    @Override
    public boolean insert(File file) {
        file.setProductId("100"+file.getFirstKindId()+file.getSecondKindId()+file.getThirdKindId()+"000001");
//        System.out.println(file.getProductId());
        file.setRegister("胡总");
        file.setFileChangeAmount(0);
        file.setCheckTag("0");
        file.setChangeTag("0");
        file.setPriceChangeTag("0");
        file.setDeleteTag("0");
        file.setDesignModuleTag("0");
        file.setDesignProcedureTag("0");
        file.setDesignCellTag("0");
        file.setRegisterTime(new Date());
        //id1,id2,id3
        QueryWrapper<ConfigFileKind> queryWrapper = new QueryWrapper<ConfigFileKind>();
        queryWrapper.eq("kind_level","1").eq("kind_id",file.getFirstKindId());
        ConfigFileKind configFileKind = configFileKindService.getOne(queryWrapper);
        file.setFirstKindName(configFileKind.getKindName());

        QueryWrapper<ConfigFileKind> queryWrapper1 = new QueryWrapper<ConfigFileKind>();
        queryWrapper1.eq("kind_level","2").eq("kind_id",file.getSecondKindId());
        ConfigFileKind configFileKind1 = configFileKindService.getOne(queryWrapper1);
        file.setSecondKindName(configFileKind1.getKindName());

        QueryWrapper<ConfigFileKind> queryWrapper2 = new QueryWrapper<ConfigFileKind>();
        queryWrapper2.eq("kind_level","3").eq("kind_id",file.getThirdKindId());
        ConfigFileKind configFileKind2 = configFileKindService.getOne(queryWrapper2);
        file.setThirdKindName(configFileKind2.getKindName());

        return this.save(file);
    }

    @Override
    public List<File> select() {
        QueryWrapper<File> queryWrapper = new QueryWrapper<File>();
        queryWrapper.eq("check_tag","0");
        return this.list(queryWrapper);
    }

    @Override
    public List<File> queryAl(File file) {
        QueryWrapper<File> queryWrapper = new QueryWrapper<File>();
        queryWrapper.eq("check_tag","1");
        if (!StringUtils.isEmpty(file.getFirstKindId())){
            queryWrapper.eq("first_kind_id",file.getFirstKindId());
        }
        if (!StringUtils.isEmpty(file.getSecondKindId())){
            queryWrapper.eq("second_kind_id",file.getSecondKindId());
        }
        if (!StringUtils.isEmpty(file.getThirdKindId())){
            queryWrapper.eq("third_kind_id",file.getThirdKindId());
        }
        if (!StringUtils.isEmpty(file.getType())){
            queryWrapper.eq("type",file.getType());
        }
        return this.list(queryWrapper);
    }

    @Override
    public boolean update(File file) {
        file.setCheckTag("1");
        file.setChecker("胡总");
        file.setCheckTime(new Date());
        return this.updateById(file);
    }

    @Override
    public boolean update2(File file) {
        File files = this.getById(file.getId());
        int num =files.getFileChangeAmount()==null?0:files.getFileChangeAmount();
        file.setChangeTag("1");
        file.setChanger("胡总");
        file.setChangeTime(new Date());
        file.setFileChangeAmount(num+1);
        return this.updateById(file);
    }


}
