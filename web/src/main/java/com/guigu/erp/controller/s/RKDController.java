package com.guigu.erp.controller.s;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guigu.erp.domain.Gather;
import com.guigu.erp.domain.GatherDetails;
import com.guigu.erp.domain.GatherDetailsDto;
import com.guigu.erp.domain.Pay;
import com.guigu.erp.service.GatherDetailsService;
import com.guigu.erp.service.GatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class RKDController {
    @Autowired
    GatherService gatherService;

    @Autowired
    GatherDetailsService gatherDetailsService;
    //条件，分页查询
    @RequestMapping("rukudan.action")
    // @CrossOrigin
    public IPage<Gather> queryallcartype(@RequestParam(value = "pageno",defaultValue = "1") int pageno,
                                      @RequestParam(value = "pagesize",defaultValue = "5") int pagesize,
                                      Gather gather){
        //组装查询条件
        QueryWrapper<Gather> queryWrapper =new QueryWrapper<Gather>();
        queryWrapper.eq("check_tag",0);
        return  gatherService.page(new Page<Gather>(pageno,pagesize),queryWrapper);
    }
    //add
    @RequestMapping("addGather.action")
    public boolean addGather(Gather gather){
        return gatherService.save(gather);
    }

    @RequestMapping("list.action")
    public String list(@RequestBody List<GatherDetailsDto> list){



       return gatherDetailsService.saveBatchExtend(list)?"操作成功":"操作失败";
    }
}
