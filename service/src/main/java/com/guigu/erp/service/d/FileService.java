package com.guigu.erp.service.d;

import com.baomidou.mybatisplus.extension.service.IService;
import com.guigu.erp.domain.d.File;

import java.util.List;

public interface FileService extends IService<File> {

    //登记
    public boolean insert(File file);

    //复核
    public boolean update(File file);

    //查询
    public List<File> select();

    //变更查询
    public List<File> queryAl(File file);

    //变更
    public boolean update2(File file);

    //删除查询
    public List<File> querydelete(File file);

    //删除
    public boolean delete(File file);

    //删除恢复查询
    public List<File> querydelete2(File file);

    //删除恢复
    public boolean delete2(File file);

    //永久删除查询
    public List<File> querydelete3();
}
