package com.guigu.erp.controller.d;

import com.guigu.erp.domain.d.File;
import com.guigu.erp.service.d.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class FilesController {

    @Autowired
    private FileService fileService;

    @RequestMapping("add")
    public boolean insert(File file){
        return fileService.insert(file);
    }

    @RequestMapping("queryfile")
    public List<File> query(){
        return fileService.select();
    }

    @RequestMapping("queryById")
    public File queryById(Integer id){
        return fileService.getById(id);
    }

    @RequestMapping("updatefile")
    public boolean update(File file){
        return fileService.update(file);
    }
}
