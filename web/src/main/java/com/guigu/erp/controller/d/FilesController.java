package com.guigu.erp.controller.d;

import com.guigu.erp.domain.PageUtil;
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

    @RequestMapping("queryAl")
    public List<File> queryAl(File file){
        return fileService.queryAl(file);
    }

    @RequestMapping("queryById")
    public File queryById(Integer id){
        return fileService.getById(id);
    }

    @RequestMapping("updatefile")
    public boolean update(File file){
        return fileService.update(file);
    }

    @RequestMapping("updatefile2")
    public boolean update2(File file){
        return fileService.update2(file);
    }

    @RequestMapping("querydelete")
    public List<File> querydelete(File file){
        return fileService.querydelete(file);
    }

    @RequestMapping("deletecs")
    public boolean deletecs(Integer id){
        File file1 = fileService.getById(id);
        return fileService.delete(file1);
    }

    @RequestMapping("querydelete2")
    public List<File> querydelete2(File file){
        return fileService.querydelete2(file);
    }

    @RequestMapping("deletecs2")
    public boolean deletecs2(Integer id){
        File file1 = fileService.getById(id);
        return fileService.delete2(file1);
    }

    @RequestMapping("querydelete3")
    public List<File> querydelete3(){
        return fileService.querydelete3();
    }

    @RequestMapping("delete")
    public boolean delete(Integer id){
        return fileService.removeById(id);
    }
}
