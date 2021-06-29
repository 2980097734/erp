package com.guigu.erp.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("S_PAY_DETAILS")
public class PayDetails {
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
    public String paidAmount;
    public String payTag;


}
