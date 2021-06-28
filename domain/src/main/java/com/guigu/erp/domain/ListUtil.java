package com.guigu.erp.domain;

import com.guigu.erp.domain.d.Module;
import com.guigu.erp.domain.d.ModuleDetails;
import lombok.Data;

import java.util.List;

@Data
public class ListUtil {
    private Module module;
    private List<ModuleDetails> moduleDetailsList;
}
