package com.guigu.erp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guigu.erp.domain.Gather;
import com.guigu.erp.domain.GatherDetails;
import com.guigu.erp.domain.GatherDetailsDto;
import com.guigu.erp.mapper.GatherDetailsMapper;
import com.guigu.erp.mapper.GatherMapper;
import com.guigu.erp.service.GatherDetailsService;
import com.guigu.erp.util.IDUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GatherDetailsServiceImpl extends ServiceImpl<GatherDetailsMapper, GatherDetails> implements GatherDetailsService {

    @Autowired
    private GatherMapper gatherMapper;

    @Transactional
    @Override
    public boolean saveBatchExtend(List<GatherDetailsDto> gatherDetails) {

        GatherDetailsDto details = gatherDetails.get(0);

        String longId = gatherMapper.LongId();
        String gatherId = IDUtil.getGatherId(longId);

        Gather gather = new Gather();



        gather.setGatherId(gatherId);
        gather.setStorer(details.getStorer());
        gather.setReason(details.getReason());
        gather.setAmountSum(details.getAmountSum());
        gather.setCostPriceSum(details.getCostPriceSum());
        gather.setGatheredAmountSum(details.getGatheredAmountSum());
        gather.setRemark(details.getRemark());
        gather.setRegister(details.getRegister());
        gather.setRegisterTime(new Date());
        gather.setChecker(details.getChecker());
        gather.setCheckTag("0");
        gather.setStorer(details.getStorer());

        gather.setCheckTag("0");
        gather.setGatherTag("1");
        gather.setCheckTime(new Date());

        int insert = gatherMapper.insert(gather);



        List<GatherDetails> gatherDetails2 =new ArrayList<GatherDetails>();
        for (GatherDetailsDto men : gatherDetails) {
            GatherDetails gatherDetails1=new GatherDetails();





            // A --->  B
            // beanutil

            try {
                BeanUtils.copyProperties(gatherDetails1,men);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }


            gatherDetails1.setParentId(gather.getId());

            gatherDetails2.add(gatherDetails1);
        }


        System.out.println(gatherDetails2);

        //boolean result = this.saveBatch(gatherDetails2);
       // if (insert > 0 && result == true)
           // return true;
        return false;
    }
}
