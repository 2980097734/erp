package com.guigu.erp.service.m.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guigu.erp.domain.m.DFile;
import com.guigu.erp.domain.m.SCell;
import com.guigu.erp.mapper.m.SCellMapper;
import com.guigu.erp.service.m.SCellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SCellServiceImpl extends ServiceImpl<SCellMapper, SCell> implements SCellService {

    @Autowired
    SCellMapper sCellMapper;

    /**
     * 制作安全库存配置单-添加
     * @param sCell
     * @return
     */
    @Override
    public boolean addSCll(SCell sCell) {
        System.out.println(sCell);

        sCell.setCheckTag("S001-0");
        return this.save(sCell);
    }
    /**
     * 安全库存配置单-复核-总数据查询
     * @return
     */
    @Override
    public PageInfo<DFile> queryAllSCll(int pageNo, int pageSize, DFile dFile, String tag,String tag2){
        System.out.println("queryAllSCll"+tag+":"+tag2);
           // 设置分页参数
        PageHelper.startPage(pageNo, pageSize);
        List<DFile> sCells = sCellMapper.queryAllSCll(tag,tag2);
        // 封装分页对象
        PageInfo<DFile> sCellPageInfo = new PageInfo<>(sCells);
        return sCellPageInfo;
    }
    /**
     *查询安全库存配置单-复核的数据
     * @param productId
     * @return
     */
    @Override
    public SCell queryByIdSCell(String productId) {
        System.out.println("queryByIdSCell的productId"+productId);
        QueryWrapper<SCell> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("PRODUCT_ID",productId);
        return  this.getOne(queryWrapper);
    }
    /**
     *查询安全库存配置单中的-复核表格数据
     * @param productId
     * @return
     */
    @Override
    public List<SCell> queryByIdSCell2(String productId) {
        System.out.println("queryByIdSCell2的productId"+productId);
        QueryWrapper<SCell> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("PRODUCT_ID",productId);
        return  this.list(queryWrapper);
    }
    /**
     * 制作安全库存配置单-复核
     * @param id,CheckTag
     * @return
     */
    @Override
    public boolean amendCheckTag(int id, String CheckTag) {
        SCell sCell = this.getById(id);
        sCell.setCheckTag("S001-1");
        return this.updateById(sCell);
    }
    /**
     * 制作安全库存配置单-修改
     * @param sCell
     * @return
     */
    @Override
    public boolean amendSCll(SCell sCell) {
        System.out.println(sCell);
        sCell.setCheckTag("S001-2");
        return this.updateById(sCell);
    }




}