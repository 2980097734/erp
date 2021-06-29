package com.guigu.erp.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("s_pay")
public class Pay {
    @TableId(value = "id",type = IdType.AUTO)
    public String id;
    //出库单编号
    public String payId;
    //出库人
    public String storer;
    /*
    出库理由
    C002-1: 生产领料
    C002-2: 赠送
    C002-3: 内部借领
    C002-4: 其他借领
      */
    public String reason;
    //出库详细理由
    public String reasonexact;
    //总件数
    public String amountSum;
    //总金额
    public String costPriceSum;
    //确认出库总件数
    public String paidAmountSum;
    //备注
    public String remark;
    //登记人
    public String register;
    //登记时间
    public String registerTime;
    //复核人
    public String checker;
        //复核时间
    public String checkTime;
    /*
    审核标志
S001-0: 等待审核
S001-1: 审核通过
S001-2: 审核不通过
     */
    public String checkTag;
    //调度人
    public String attemper;
    //调度时间
    public String attemperTime;
    //库存标志
    //K002-1: 已登记
    //K002-2: 已调度
    public String payTag;

}
