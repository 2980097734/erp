package com.guigu.erp.domain.m;



import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@TableName("m_apply")
public class Apply {
    @TableId("id")
    private int id;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("register_time")
    private Date registerTime;
    private String checker;
    @TableField("check_suggestion")
    private String checkSuggestion;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("check_time")
    private Date checkTime;
    @TableField("check_tag")
    private String checkTag;
    @TableField("manufacture_tag")
    private String manufactureTag;

    @TableField(exist = false)
    List<Apply> All;
}
