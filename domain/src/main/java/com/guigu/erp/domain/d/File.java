package com.guigu.erp.domain.d;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("d_file")
public class File {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String productId;
    private String productName;
    private String factoryName;
    private String firstKindId;
    private String firstKindName;
    private String secondKindId;
    private String secondKindName;
    private String thirdKindId;
    private String thirdKindName;
    private String productNick;
    private String type;
    private String productClass;
    private String personalUnit;
    private String personalValue;
    private String providerGroup;
    private String warranty;
    private String twinName;
    private String twinId;
    private String lifecycle;
    private Double listPrice;
    private Double costPrice;
    private Double realCostPrice;
    private String amountUnit;
    private String productDescribe;
    private String responsiblePerson;
    private String register;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date registerTime;
    private String checker;
    private Date checkTime;
    private String checkTag;
    private String changer;
    private Date changeTime;
    private String changeTag;
    private String priceChangeTag;
    private Integer fileChangeAmount;
    private String deleteTag;
    private String designModuleTag;
    private String designProcedureTag;
    private String designCellTag;
}
