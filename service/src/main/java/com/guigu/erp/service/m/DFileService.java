package com.guigu.erp.service.m;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guigu.erp.domain.m.DFile;

import java.util.List;

public interface DFileService extends IService<DFile> {

    /**
     * 根据复核状态查询生产工序设计单数据
     * 状态：
     * S001-0: 等待审核
     * S001-1: 审核通过
     * S001-2: 审核不通过
     * @param state
     * @return
     */
//    List<DFile> queryByState(String state);
    /**
     *查询安全库存配置单表格数据
     * @param pageno
     * @param pagesize
     * @param dFile
     * @return
     */
    IPage<DFile> queryAllDFile(int pageno, int pagesize, DFile dFile);

    /**
     *查询安全库存配置单的数据
     * @param productId
     * @return
     */
    DFile queryByIdDFile(String productId);

    /**
     *查询安全库存配置单中的表格数据
     * @param productId
     * @return
     */
    List<DFile> queryByIdDFile2(String productId);

}
