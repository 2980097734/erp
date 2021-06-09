package com.guigu.erp.domain.m;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("s_cell")
public class Cell {
    @TableId("id")
    private  String Code;
    private  String Id;
    private  String StoreId;
    private  String ProductId;
    private  String ProductName;
    private  String FirstKindId;
    private  String FirstKindName;
    private  String SecondKindId;
    private  String SecondKindName;
    private  String ThirdKindId;
    private  String ThirdKindName;
    private  String MinAmount;
    private  String MaxAmount;
    private  String MaxCapacityAmount;
    private  String Amount;
    private  String Config;
    private  String Register;
    private  String RegisterTime;
    private  String Checker;
    private  String CheckTime;
    private  String CheckTag;
}
