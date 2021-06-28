package com.guigu.erp.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("S_GATHER")
public class Gather {
    @TableId(value = "id",type = IdType.AUTO)
    public String id;
    public String gatherId;
    public String storer;
    public String reason;
    public String reasonexact;
    public String amountSum;
    public String costPriceSum;
    public String gatheredAmountSum;
    public String remark;
    public String  register;
    public Date registerTime;
    public String checker;
    public Date checkTime;
    public String checkTag;
    public String attemper;
    public String attemperTime;
    public String gatherTag;

}
