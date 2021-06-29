package com.guigu.erp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guigu.erp.domain.File;
import com.guigu.erp.mapper.FileMapper;
import com.guigu.erp.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class FileController {

    @Autowired
    FileService fileService;

    //条件，分页查询
    @RequestMapping("queryallcp.action")
    // @CrossOrigin
    public IPage<File> queryallcp(@RequestParam(value = "pageno",defaultValue = "1") int pageno,
                                       @RequestParam(value = "pagesize",defaultValue = "10") int pagesize,
                                       File file){

        return  fileService.page(new Page<File>(pageno,pagesize));
    }
}
