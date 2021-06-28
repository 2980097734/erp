package com.guigu.erp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.guigu.erp.domain.GatherDetails;
import com.guigu.erp.domain.GatherDetailsDto;

import java.util.List;

public interface GatherDetailsService extends IService<GatherDetails> {
   boolean saveBatchExtend(List<GatherDetailsDto> gatherDetails);
}
