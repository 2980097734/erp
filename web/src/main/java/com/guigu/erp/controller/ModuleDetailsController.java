package com.guigu.erp.controller;

import com.guigu.erp.domain.ResultUtil;
import com.guigu.erp.domain.d.ModuleDetails;
import com.guigu.erp.service.d.ModuleDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/moduleDetails")
public class ModuleDetailsController {
    @Autowired
    private ModuleDetailsService moduleDetailsService;

    /**
     * 不通过
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public ResultUtil delete(int id, int parentId){
        return  moduleDetailsService.deleteById(id,parentId);
    }

    /**
     * 删除
     * @param productId
     * @return
     */
    @RequestMapping("/selectByProductId")
    public List<ModuleDetails   > selectByProductId(String productId){
        return  moduleDetailsService.selectByProductId(productId);
    }
}
