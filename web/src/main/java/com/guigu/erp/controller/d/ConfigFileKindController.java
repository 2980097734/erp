package com.guigu.erp.controller.d;

import com.guigu.erp.domain.d.ConfigFileKind;
import com.guigu.erp.service.d.ConfigFileKindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class ConfigFileKindController {

    @Autowired
    ConfigFileKindService configFileKindService;

    @RequestMapping("queryconfig")
    public List<ConfigFileKind> queryAll(){
        return configFileKindService.queryconfig();
    }
}
