package com.guigu.erp.service.d;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.guigu.erp.domain.ResultUtil;
import com.guigu.erp.domain.d.File;

import java.util.List;

public interface FileService extends IService<File> {

    public boolean insert(File file);

    public boolean update(File file);

    public List<File> select();

    ResultUtil checkName(String name);
    PageInfo queryPage(int pageNo, int pageSize, File file);
    boolean checkTag(File file);
    boolean updateFile(File file);
    ResultUtil deleteById(int id);
    ResultUtil recoveryById(int id);
    ResultUtil foreverDelete(int id);
    boolean selectByProductId(String productId);

    public List<File> queryAl(File file);

    public boolean update2(File file);
}
