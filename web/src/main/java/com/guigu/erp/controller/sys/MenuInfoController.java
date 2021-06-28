package com.guigu.erp.controller.sys;


import com.guigu.erp.domain.sys.MenuInfo;
import com.guigu.erp.service.sys.MenuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("menu")
public class MenuInfoController {

    @Autowired
    MenuInfoService menuInfoService;

    @RequestMapping("queryAllmenu")
    public List<MenuInfo> queryAllmenus(){
        return menuInfoService.queryAllmenu();
    }

    @RequestMapping("queryUser")
    public String queryUser(){
        return "admin";
    }
}
