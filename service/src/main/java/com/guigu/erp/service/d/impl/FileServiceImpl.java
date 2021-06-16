package com.guigu.erp.service.d.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guigu.erp.domain.d.File;
import com.guigu.erp.mapper.d.FileMapper;
import com.guigu.erp.service.d.FileService;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {
    @Override
    public boolean insert(File file) {
        file.setProductId("100"+file.getFirstKindId()+file.getSecondKindId()+file.getThirdKindId()+"000001");
//        System.out.println(file.getProductId());
        file.setRegister("胡总");
        file.setCheckTag("S001-0");
        file.setChangeTag("D002-0");
        file.setPriceChangeTag("J001-0");
        file.setDeleteTag("C001-0");
        file.setDesignModuleTag("W001-0");
        file.setDesignProcedureTag("G001-0");
        file.setDesignCellTag("K001-0");
        return this.save(file);
    }
}
