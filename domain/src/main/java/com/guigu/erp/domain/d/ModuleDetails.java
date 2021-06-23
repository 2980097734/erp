package com.guigu.erp.domain.d;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("d_module_details")
public class ModuleDetails {
    @TableId("id")
    private Integer id;
    private Integer parentId;
    private Integer detailsNumber;
    private String productId;
    private String productName;
    private String type;
    private String productDescribe;
    private String amountUnit;
    private Integer amount;
    private Integer residualAmount;
    private Double costPrice;
    private Double subtotal;
    @TableField(value = "goodsId",exist = false)
    private String goodsId;
    @TableField(value = "moduleDescribe",exist = false)
    private String moduleDescribe;
    @TableField(value = "designer",exist = false)
    private String designer;
    @TableField(value = "register",exist = false)
    private String register;
    @TableField(value = "registerTime",exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date registerTime;
}
