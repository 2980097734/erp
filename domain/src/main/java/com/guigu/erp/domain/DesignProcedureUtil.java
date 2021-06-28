package com.guigu.erp.domain;


import lombok.Data;

import java.util.List;

@Data
public class DesignProcedureUtil {
    private DesignProcedure designProcedure;
    private List<DesignProcedureDetails> designProcedureDetails;
    private List<DesignProcedureModule> designProcedureModules;
}
