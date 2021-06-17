package com.guigu.erp.domain.m;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("m_apply")
public class Apply {
    @TableId("id")
    private String id;
    private String applyId;
    private String productId;
    private String productName;
    private String productDescribe;
    private String type;
    private String amount;
    private String designer;
    private String remark;
    private String register;
    private Date registerTime;
    private String checker;
    private String checkSuggestion;
    private Date checkTime;
    private String checkTag;
    private String manufactureTag;
}
