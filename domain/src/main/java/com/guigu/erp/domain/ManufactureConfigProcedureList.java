package com.guigu.erp.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("manufacture_config_procedure_list")
public class ManufactureConfigProcedureList {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String kind;
    private String typeId;
    private String typeName;
    private String describe1;

}
