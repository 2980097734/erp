package com.guigu.erp.domain.d;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("d_module")
public class Module {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String designId;
    private String productId;
    private String productName;
    private String firstKindId;
    private String firstKindName;
    private String secondKindId;
    private String secondKindName;
    private String thirdKindId;
    private String thirdKindName;
    private String designer;
    private String moduleDescribe;
    private Double costPriceSum;
    private String register;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date registerTime;
    private String checker;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date checkTime;
    private String changer;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date changeTime;
    private String checkTag;
    private String changeTag;
}
