package com.guigu.erp.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("m_design_procedure_module")
public class DesignProcedureModule {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer parentId;

    private Integer detailsNumber;

    private String productId;

    private String productName;

    private String type;

    private BigDecimal amount;

    private String productDescribe;

    private String amountUnit;

    private BigDecimal costPrice;

    private BigDecimal subtotal;


}
