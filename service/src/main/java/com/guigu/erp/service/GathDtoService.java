package com.guigu.erp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.guigu.erp.domain.GatherDetailsDto;

import java.util.List;

public interface GathDtoService extends IService<GatherDetailsDto> {
    List<GatherDetailsDto> gathShenhe(int id);
}
