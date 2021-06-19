package com.guigu.erp.domain;

import com.guigu.erp.domain.d.File;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

public class IDUtil {
    //getProductId
    public static String getProductId(String longId, File file){
        String substring ="";
        if (longId != null) {
            substring = longId.substring(longId.length()-6);
            AtomicLong at = new AtomicLong(Long.parseLong(substring));
            Long atLong = at.incrementAndGet();
            return "100" + file.getFirstKindId() + file.getSecondKindId() + file.getThirdKindId()+ String.format("%06d", atLong);
         } else {
            return "100" + file.getFirstKindId() + file.getSecondKindId() + file.getThirdKindId() + "000001";
        }
    }
    //物料设计单编号  200+当前日期+4位流水号
    public static String getDesignId(String longId){
        String substring ="";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateStr = sdf.format(new Date());
        if (longId != null) {
            substring = longId.substring(longId.length()-4);
            AtomicLong at = new AtomicLong(Long.parseLong(substring));
            Long atLong = at.incrementAndGet();
            return "200" +dateStr + String.format("%04d", atLong);
        } else {
            return "200" + dateStr +  "0001";
        }
    }
    //生产计划编号  300+当前日期+4位流水号
    public static String getApplyId(String longId){
        String substring ="";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateStr = sdf.format(new Date());
        if (longId != null) {
            substring = longId.substring(longId.length()-4);
            AtomicLong at = new AtomicLong(Long.parseLong(substring));
            Long atLong = at.incrementAndGet();
            return "300" +dateStr + String.format("%04d", atLong);
        } else {
            return "300" + dateStr +  "0001";
        }
    }
}
