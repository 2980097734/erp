package com.guigu.erp.service.m;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.guigu.erp.domain.m.DFile;
import com.guigu.erp.domain.m.SCell;

import java.util.Date;
import java.util.List;

public interface SCellService extends IService<SCell> {
    /**
     * 制作安全库存配置单-添加
     * @param sCell
     * @return
     */
    boolean addSCll(SCell sCell);
    /**
     * 安全库存配置单-复核-总数据查询
     * @return
     */
    PageInfo<DFile> queryAllSCll(int pageNo, int pageSize, DFile dFile, String tag, String tag2);
    /**
     *查询安全库存配置单-复核的数据
     * @param id
     * @return
     */
    SCell queryByIdSCell(String id);
    /**
     *查询安全库存配置单中的-复核表格数据
     * @param id
     * @return
     */
    List<SCell> queryByIdSCell2(String id);
    /**
     * 制作安全库存配置单-复核
     * @param id,CheckTag
     * @return
     */
    boolean amendCheckTag(int id, String CheckTag, String checker, Date checkTime);
    /**
     * 制作安全库存配置单-修改
     * @param sCell
     * @return
     */
    public boolean amendSCll(SCell sCell);
}
