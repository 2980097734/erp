package com.guigu.erp.domain.m;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 注：
 * 1、产品档案每变更一次，则FILE_CHANGE_AMOUNT加1
 */
@Data
@TableName("d_file")
public class DFile {


    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;//序号

    @TableField("PRODUCT_ID")
    private String productId;//产品编号

    @TableField("PRODUCT_NAME")
    private String productName;//产品名称

    @TableField("FACTORY_NAME")
    private String factoryName;//制造商

    @TableField("FIRST_KIND_ID")
    private String firstKindId;//产品I级分类编号

    @TableField("FIRST_KIND_NAME")
    private String firstKindName;//产品I级分类名称

    @TableField("SECOND_KIND_ID")
    private String secondKindId;//产品II级分类编号

    @TableField("SECOND_KIND_NAME")
    private String secondKindName;//产品II级分类名称

    @TableField("THIRD_KIND_ID")
    private String thirdKindId;//产品III级分类编号

    @TableField("THIRD_KIND_NAME")
    private String thirdKindName;//产品III级分类名称

    @TableField("PRODUCT_NICK")
    private String productNick;//产品简称

    @TableField("TYPE")
    private String type;//用途类型,Y001-1: 商品, Y001-2: 物料

    @TableField("PRODUCT_CLASS")
    private String productClass;//档次级别,D001-1: 高档,D001-2: 中档,D001-3: 低档

    @TableField("PERSONAL_UNIT")
    private String personalUnit;//计量单位

    @TableField("PERSONAL_VALUE")
    private String personalValue;//计量值

    @TableField("PROVIDER_GROUP")
    private String providerGroup;//供应商集合

    @TableField("WARRANTY")
    private String warranty;//保修期

    @TableField("TWIN_NAME")
    private String twinName;//替代品名称

    @TableField("TWIN_ID")
    private String twinId;//替代品编号

    @TableField("LIFECYCLE")
    private String lifecycle;//生命周期

    @TableField("LIST_PRICE")
    private Double listPrice;//市场单价

    @TableField("COST_PRICE")
    private Double costPrice;//计划成本单价

    @TableField("REAL_COST_PRICE")
    private Double realCostPrice;//成本单价

    @TableField("AMOUNT_UNIT")
    private String amountUnit;//单位

    @TableField("PRODUCT_DESCRIBE")
    private String productDescribe;//产品描述

    @TableField("RESPONSIBLE_PERSON")
    private String responsiblePerson;//产品经理

    @TableField("REGISTER")
    private String register;//登记人

    @TableField("REGISTER_TIME")
    private Date registerTime;//建档时间

    @TableField("CHECKER")
    private String checker;//复核人

    @TableField("CHECK_TIME")
    private Date checkTime;//复核时间

    @TableField("CHECK_TAG")
    private String checkTag;//审核标志,S001-0: 等待审核,S001-1: 审核通过,S001-2: 审核不通过

    @TableField("CHANGER")
    private String changer;//变更人

    @TableField("CHANGE_TIME")
    private Date changeTime;//变更时间

    @TableField("CHANGE_TAG")
    private String changeTag;/*档案变更标志,D002-0: 未变更,D002-1: 已变更*/

    @TableField("PRICE_CHANGE_TAG")
    private String priceChangeTag;/*价格变更标志,J001-0：未变更,J001-1：已变更*/

    @TableField("FILE_CHANGE_AMOUNT")
    private Integer fileChangeAmount;//档案变更累计

    @TableField("DELETE_TAG")
    private String deleteTag;/*产品删除标志,C001-0: 未删除,C001-1: 已删除*/

    @TableField("DESIGN_MODULE_TAG")
    private String designModuleTag;/*物料组成标志,W001-0: 未设计,W001-1: 已设计*/

    @TableField("DESIGN_PROCEDURE_TAG")
    private String designProcedureTag;/*工序组成标志,G001-0: 未设计,G001-1: 已设计*/



    @TableField("DESIGN_CELL_TAG")
    private String designCellTag;/*库存分配标志,K001-0: 未设计,K001-1: 已设计*/



}
