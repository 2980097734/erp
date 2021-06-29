package com.guigu.erp.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("S_GATHER_DETAILS")
public class GatherDetails {
    @TableId(value = "id",type = IdType.AUTO)
    public String id;
    public String parentId;
    public String productId;
    public String productName;
    public String productDescribe;
    public String amount;
    public String amountUnit;
    public String costPrice;
    public String subtotal;
    public String gatheredAmount;
    public String gatherTag;



    @TableField(value = "gatherId",exist = false)
    public String gatherId;
    @TableField(value = "storer",exist = false)
    public String storer;
    @TableField(value = "reason",exist = false)
    public String reason;
    @TableField(value = "reasonexact",exist = false)
    public String reasonexact;
    @TableField(value = "amountSum",exist = false)
    public String amountSum;
    @TableField(value = "costPriceSum",exist = false)
    public String costPriceSum;
    @TableField(value = "gatheredAmountSum",exist = false)
    public String gatheredAmountSum;
    @TableField(value = "remark",exist = false)
    public String remark;
    @TableField(value = "register",exist = false)
    public String  register;
    @TableField(value = "registerTime",exist = false)
    public String registerTime;
    @TableField(value = "checker",exist = false)
    public String checker;
    @TableField(value = "checkTime",exist = false)
    public String checkTime;
    @TableField(value = "checkTag",exist = false)
    public String checkTag;
    @TableField(value = "attemper",exist = false)
    public String attemper;
    @TableField(value = "attemperTime",exist = false)
    public String attemperTime;

}
