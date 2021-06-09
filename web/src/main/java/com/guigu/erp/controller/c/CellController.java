package com.guigu.erp.controller.c;


import com.guigu.erp.domain.m.Cell;
import com.guigu.erp.service.m.CellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("Cell")
public class CellController {

    @Autowired
    CellService cellService;

    @RequestMapping("add.action")
    public boolean addCell(Cell cell){
        return cellService.save(cell);
    }
}
