package com.guigu.erp.domain;

import lombok.Data;

@Data
public class GatherDetailsDto extends GatherDetails{
    public String productName;
    public String productId;
    public String productDescribe;
    public String personalValue;
    public String amountUnit;
    public String listPrice;
    public String personalUnit;

}
