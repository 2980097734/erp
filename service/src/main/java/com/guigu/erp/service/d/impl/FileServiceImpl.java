package com.guigu.erp.service.d.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guigu.erp.domain.ResultUtil;
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
    private FileMapper fileMapper;
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
    public ResultUtil checkName(String name) {
        ResultUtil<File> resultUtil = new ResultUtil<File>();
        //判断产品名是否存在
        QueryWrapper<File> queryWrapper = new QueryWrapper<File>();
        queryWrapper.eq("product_name", name);
        File file = this.getOne(queryWrapper);
        if (file != null && !"2".equals(file.getDeleteTag())) {
            resultUtil.setMessage("用户名已存在");
            resultUtil.setResult(false);
            return resultUtil;
        } else {
            resultUtil.setMessage("用户名可以使用");
            resultUtil.setResult(true);
            return resultUtil;
        }
}

    @Override
    public PageInfo queryPage(int pageNo, int pageSize, File file) {
        List<File> fileList = null;
        if (file != null) {
            //追加条件 产品名称
            QueryWrapper<File> queryWrapper = new QueryWrapper<File>();
            if (file.getProductName() != null && file.getProductName() != "")
                queryWrapper.like("product_name", file.getProductName());
            // 追加条件 产品i级分类编号
            if (file.getFirstKindId() != null && file.getFirstKindId() != "")
                queryWrapper.eq("first_kind_id", file.getFirstKindId());
            // 追加条件 产品ii级分类编号
            if (file.getSecondKindId() != null && file.getSecondKindId() != "")
                queryWrapper.eq("second_kind_id", file.getSecondKindId());
            // 追加条件 产品iii级分类编号
            if (file.getThirdKindId() != null && file.getThirdKindId() != "")
                queryWrapper.eq("third_kind_id", file.getThirdKindId());
            // 追加条件 建档时间开始
            if (file.getRegisterTime() != null)
                queryWrapper.ge("register_time", file.getRegisterTime());
            // 追加条件 建档时间结束
            if (file.getRegisterTime2() != null )
                queryWrapper.le("register_time", file.getRegisterTime2());
            //审核标志0: 等待1: 通过2: 不通过
            if (file.getCheckTag() != null && file.getCheckTag() != "")
                queryWrapper.eq("check_tag", file.getCheckTag());
            //产品删除标志0: 未删除1: 已删除2永久删除
            if (file.getDeleteTag() != null && file.getDeleteTag() != "")
                queryWrapper.eq("delete_tag", file.getDeleteTag());
            //类型
            if (file.getType() != null && file.getType() != "")
                queryWrapper.eq("type", file.getType());
            //design_module_tag物料组成标志0    designModuleTag:1,
            if (file.getDesignModuleTag() != null && file.getDesignModuleTag() != "")
                queryWrapper.eq("design_module_tag", file.getDesignModuleTag());
            // designProcedureTag:1,designProcedureTag
            if (file.getDesignProcedureTag() != null && file.getDesignProcedureTag() != "")
                queryWrapper.eq("design_procedure_tag", file.getDesignProcedureTag());
            PageHelper.startPage(pageNo, pageSize);
            fileList = this.list(queryWrapper);
        } else {
            PageHelper.startPage(pageNo, pageSize);
            fileList = this.list();
        }
        return new PageInfo<File>(fileList);
    }

    @Override
    public boolean checkTag(File f) {
        File file = this.getById(f.getId());
        file.setCheckTag(f.getCheckTag());
        //复核人
        file.setChecker(f.getChecker());
        //复核时间
        file.setCheckTime(f.getCheckTime());
        return this.updateById(file);
    }

    @Override
    public boolean update(File file) {
        file.setCheckTag("1");
        file.setChecker("胡总");
        file.setCheckTime(new Date());

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

        return this.updateById(file);
    }

    @Override
    public boolean updateFile(File file) {
        //未复核
        file.setCheckTag("0");
        //档案变更标志
        file.setChangeTag("1");
        /*//变更时间
        file.setChangeTime(new Date());*/
        //产品档案每变更一次,则file_change_amount加1
        file.setFileChangeAmount(file.getFileChangeAmount() + 1);
        return this.updateById(file);
    }

    @Override
    public ResultUtil deleteById(int id) {
        ResultUtil<Object> resultUtil = new ResultUtil<Object>();
        QueryWrapper<File> usersQueryWrapper = new QueryWrapper<File>();
        usersQueryWrapper.eq("id", id);
        File file = this.getOne(usersQueryWrapper);
        file.setDeleteTag("1");
        boolean result = this.updateById(file);
        if (result) {
            resultUtil.setResult(true);
            resultUtil.setMessage("删除成功");
            return resultUtil;
        } else
            resultUtil.setResult(false);
        resultUtil.setMessage("删除失败");
        return resultUtil;
    }

    @Override
    public ResultUtil recoveryById(int id) {
        ResultUtil<Object> resultUtil = new ResultUtil<Object>();
        QueryWrapper<File> usersQueryWrapper = new QueryWrapper<File>();
        usersQueryWrapper.eq("id", id);
        File file = this.getOne(usersQueryWrapper);
        file.setCheckTag("0");
        file.setDeleteTag("0");
        file.setFileChangeAmount(file.getFileChangeAmount() + 1);
        boolean result = this.updateById(file);
        if (result) {
            resultUtil.setResult(true);
            resultUtil.setMessage("恢复成功");
            return resultUtil;
        } else
            resultUtil.setResult(false);
        resultUtil.setMessage("恢复失败");
        return resultUtil;
    }

    @Override
    public ResultUtil foreverDelete(int id) {
        ResultUtil<Object> resultUtil = new ResultUtil<Object>();
        QueryWrapper<File> usersQueryWrapper = new QueryWrapper<File>();
        usersQueryWrapper.eq("id", id);
        File file = this.getOne(usersQueryWrapper);
        file.setDeleteTag("2");
        boolean result = this.updateById(file);
        if (result) {
            resultUtil.setResult(true);
            resultUtil.setMessage("删除成功");
            return resultUtil;
        } else
            resultUtil.setResult(false);
        resultUtil.setMessage("删除失败");
        return resultUtil;
    }

    @Override
    public boolean selectByProductId(String productId) {
        File file = fileMapper.selectByProductId(productId);
        if (file!=null)
            return true;
        return false;
    }
}
