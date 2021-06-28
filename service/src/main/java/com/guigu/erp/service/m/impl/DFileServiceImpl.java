package com.guigu.erp.service.m.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guigu.erp.domain.m.DFile;
import com.guigu.erp.mapper.m.DFileMapper;
import com.guigu.erp.service.m.DFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class DFileServiceImpl extends ServiceImpl<DFileMapper, DFile> implements DFileService {

    @Autowired
    DFileMapper dFileMapper;

    /**
     * 根据复核状态查询生产工序设计单数据
     * 状态：
     * S001-0: 等待审核
     * S001-1: 审核通过
     * S001-2: 审核不通过
     * @param state
     * @return
     */
//    @Override
//    public List<DFile> queryByState(String state) {
//        QueryWrapper<DFile> queryWrapper = new QueryWrapper<DFile>();
//        queryWrapper.eq("CHECK_TAG", state);
//        return dFileMapper.selectList(queryWrapper);
//    }

    /**
     *查询安全库存配置单表格数据
     * @param pageno
     * @param pagesize
     * @param dFile
     * @return
     */
    @Override
    public IPage<DFile> queryAllDFile(int pageno, int pagesize, DFile dFile) {
        QueryWrapper<DFile> queryWrapper = new QueryWrapper<DFile>();
        queryWrapper.eq("CHECK_TAG","1");
//        hhy
        if(!StringUtils.isEmpty(dFile.getProductName())){
            queryWrapper.like("name",dFile.getProductName());
        }
        return this.page(new Page<DFile>(pageno,pagesize), queryWrapper);
    }

    /**
     *查询安全库存配置单的数据
     * @param productId
     * @return
     */
    @Override
    public DFile queryByIdDFile(String productId) {
        System.out.println("queryByIdDFile1的productId"+productId);
        QueryWrapper<DFile> queryWrapper = new QueryWrapper<DFile>();
        queryWrapper.eq("PRODUCT_ID",productId);
        return  this.getOne(queryWrapper);
    }
    /**
     *查询安全库存配置单中的表格数据
     * @param productId
     * @return
     */
    @Override
    public List<DFile> queryByIdDFile2(String productId) {
        System.out.println("queryByIdDFile2的productId"+productId);
        QueryWrapper<DFile> queryWrapper = new QueryWrapper<DFile>();
        queryWrapper.eq("PRODUCT_ID",productId);
        return  this.list(queryWrapper);
    }
}
