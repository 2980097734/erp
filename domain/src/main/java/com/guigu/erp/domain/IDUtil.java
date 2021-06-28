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
    //入库单编号	401+当前日期+4位流水号
    public static String getGatherId(String longId){
        String substring ="";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateStr = sdf.format(new Date());
        if (longId != null) {
            substring = longId.substring(longId.length()-4);
            AtomicLong at = new AtomicLong(Long.parseLong(substring));
            Long atLong = at.incrementAndGet();
            return "401" +dateStr + String.format("%04d", atLong);
        } else {
            return "401" + dateStr +  "0001";
        }
    }
    //生产计划编号  300+当前日期+4位流水号
    public static String getManufactureId(String longId){
        String substring ="";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateStr = sdf.format(new Date());
        if (longId != null) {
            substring = longId.substring(longId.length()-4);
            AtomicLong at = new AtomicLong(Long.parseLong(substring));
            Long atLong = at.incrementAndGet();
            return "500" +dateStr + String.format("%04d", atLong);
        } else {
            return "500" + dateStr +  "0001";
        }
    }
    //工序设计单编号	201+当前日期+4位流水号
    public static String getDesignProcedureId(String longId){
        String substring ="";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateStr = sdf.format(new Date());
        if (longId != null) {
            substring = longId.substring(longId.length()-4);
            AtomicLong at = new AtomicLong(Long.parseLong(substring));
            Long atLong = at.incrementAndGet();
            return "201" +dateStr + String.format("%04d", atLong);
        } else {
            return "201" + dateStr +  "0001";
        }
    }
    //出库单编号	402+当前日期+4位流水号
    public static String getPayId(String longId){
        String substring ="";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateStr = sdf.format(new Date());
        if (longId != null) {
            substring = longId.substring(longId.length()-4);
            AtomicLong at = new AtomicLong(Long.parseLong(substring));
            Long atLong = at.incrementAndGet();
            return "402" +dateStr + String.format("%04d", atLong);
        } else {
            return "402" + dateStr +  "0001";
        }
    }
}
