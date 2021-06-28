package com.guigu.erp.controller;

import com.guigu.erp.domain.ManufactureConfigProcedureList;
import com.guigu.erp.service.d.ManufactureConfigProcedureListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/manufactureConfigProcedureList")
public class ManufactureConfigProcedureListController {
    @Autowired
    private ManufactureConfigProcedureListService manufactureConfigProcedureListService;

    @RequestMapping("/list")
    public List<ManufactureConfigProcedureList> list(){
        return manufactureConfigProcedureListService.list();
    }
}
