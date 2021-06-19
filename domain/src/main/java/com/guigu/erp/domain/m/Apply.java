package com.guigu.erp.domain.m;



import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@TableName("m_apply")
public class Apply {
    @TableId("id")
    private String id;
    @TableField("apply_id")
    private String applyId;
    @TableField("product_id")
    private String productId;
    @TableField("product_name")
    private String productName;
    @TableField("product_describe")
    private String productDescribe;
    private String type;
    private String amount;
    private String designer;
    private String remark;
    private String register;
    @TableField("register_time")
    private Date registerTime;
    private String checker;
    @TableField("check_suggestion")
    private String checkSuggestion;
    @TableField("check_time")
    private Date checkTime;
    @TableField("check_tag")
    private String checkTag;
    @TableField("manufacture_tag")
    private String manufactureTag;

    @TableField(exist = false)
    List<Apply> All;
}
