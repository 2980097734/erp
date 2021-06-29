package com.guigu.erp.controller.s;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guigu.erp.domain.Pay;
import com.guigu.erp.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class CKDController {
    @Autowired
    PayService payService;
    //条件，分页查询
    @RequestMapping("chukudan.action")
    // @CrossOrigin
    public IPage<Pay> queryallcartype(@RequestParam(value = "pageno",defaultValue = "1") int pageno,
                                      @RequestParam(value = "pagesize",defaultValue = "10") int pagesize,
                                      Pay pay){

        //组装查询条件
        QueryWrapper<Pay> queryWrapper =new QueryWrapper<Pay>();



        return  payService.page(new Page<Pay>(pageno,pagesize),queryWrapper);


    }
}
