package com.guigu.erp.service.d;

import com.baomidou.mybatisplus.extension.service.IService;
import com.guigu.erp.domain.d.File;

import java.util.List;

public interface FileService extends IService<File> {

    public boolean insert(File file);

    public boolean update(File file);

    public List<File> select();
}
