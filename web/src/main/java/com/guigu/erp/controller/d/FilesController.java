package com.guigu.erp.controller.d;

import com.guigu.erp.domain.d.File;
import com.guigu.erp.service.d.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class FilesController {

    @Autowired
    private FileService fileService;

    @RequestMapping("add")
    public boolean insert(File file){
        return fileService.save(file);
    }
}
