package com.guigu.erp.controller.s;


import com.guigu.erp.domain.s.Cell;
import com.guigu.erp.service.s.CellService;
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
