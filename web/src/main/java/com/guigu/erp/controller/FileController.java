package com.guigu.erp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.guigu.erp.domain.ResultUtil;
import com.guigu.erp.domain.d.File;
import com.guigu.erp.service.d.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/files")
public class FileController {
    @Autowired
    private FileService fileService;


    /**
     * 新增
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "/insert", produces = "application/json;charset=utf-8")
    public boolean insert(File file) {
        return fileService.insert(file);
    }

    /**
     * 查询产品名是否存在可用
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "/checkName", produces = "application/json;charset=utf-8")
    public ResultUtil checkName(String name) {
        return fileService.checkName(name);
    }

    /**
     * 复核条件分页查询角色
     *
     * @param pageNo
     * @param pageSize
     * @return
     */

    @RequestMapping("/page")
    public PageInfo<File> page(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                               File file) {
        return fileService.queryPage(pageNo, pageSize, file);
    }

    /**
     * 单个查询
     *
     * @param id
     * @return
     */
    @RequestMapping("/selectById/{id}")
    public File selectById(@PathVariable int id) {
        return fileService.getById(id);
    }

    /**
     * 复核通过
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/checkTag", produces = "application/json;charset=utf-8")
    public boolean checkTag(File file) {
        return fileService.checkTag(file);
    }

    /**
     * 修改
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "/update", produces = "application/json;charset=utf-8")
    public boolean update(File file) {
        return fileService.updateFile(file);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete/{id}")
    public ResultUtil delete(@PathVariable int id) {
        return fileService.deleteById(id);
    }


    /**
     * //恢复档案
     *
     * @param id
     * @return
     */
    @RequestMapping("/recovery/{id}")
    public ResultUtil recovery(@PathVariable int id) {
        return fileService.recoveryById(id);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @RequestMapping("/remove/{id}")
    public ResultUtil remove(@PathVariable int id) {
        return fileService.foreverDelete(id);
    }

    @RequestMapping("/Filespage")
    public IPage<File> Filespage(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                                 @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                 File file) {

        //组装查询条件
        QueryWrapper<File> queryWrapper = new QueryWrapper<File>();
        queryWrapper.eq("design_procedure_tag", 0);
        if (!StringUtil.isEmpty(file.getProductName())) {
            queryWrapper.like("product_name", file.getProductName());
        }
        if (!StringUtil.isEmpty(file.getFirstKindName())) {
            queryWrapper.like("first_kind_name", file.getFirstKindName());
        }
        if (!StringUtil.isEmpty(file.getSecondKindName())) {
            queryWrapper.like("second_kind_name", file.getSecondKindName());
        }
        if (!StringUtil.isEmpty(file.getThirdKindName())) {
            queryWrapper.like("third_kind_name", file.getThirdKindName());
        }

        return fileService.page(new Page<File>(pageNo, pageSize), queryWrapper);
    }

    @RequestMapping("/fisByid")
    public File fisbyid(int id) {

        return fileService.getById(id);

    }

    @RequestMapping("/selectByProductId")
    public boolean selectByProductId(String productId){
        return fileService.selectByProductId(productId);
    }
    @RequestMapping("/FilePages2")
    public IPage<File> FilePages2(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                                  @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {


        return fileService.page(new Page<File>(pageNo, pageSize));
    }

}
