package com.guigu.erp.domain.m;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@TableName("m_manufacture")
public class Manufacture {
    @TableId("id")
    private int id;
    @TableField("manufacture_id")
    private String manufactureId;
    @TableField("product_id")
    private String productId;
    @TableField("product_name")
    private String productName;
    private String amount;
    @TableField("tested_amount")
    private String testedAmount;
    @TableField("apply_id_group")
    private String applyIdGroup;
    @TableField("product_describe")
    private String productDescribe;
    @TableField("module_cost_price_sum")
    private String moduleCostPriceSum;
    @TableField("real_module_cost_price_sum")
    private String realModuleCostPriceSum;
    @TableField("labour_cost_price_sum")
    private String labourCostPriceSum;
    @TableField("real_labour_cost_price_sum")
    private String realLabourCostPriceSum;
    private String designer;
    private String register;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("register_time")
    private String registerTime;
    private String checker;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("check_time")
    private String checkTime;
    private String remark;
    @TableField("check_tag")
    private String checkTag;
    @TableField("manufacture_procedure_tag")
    private String manufactureProcedureTag;
}
