package com.guigu.erp.service.m.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guigu.erp.domain.m.Manufacture;
import com.guigu.erp.mapper.m.ManufactureMapper;
import com.guigu.erp.service.m.ManufactureService;
import org.springframework.stereotype.Service;

@Service
public class ManufactureServiceImpl extends ServiceImpl<ManufactureMapper, Manufacture> implements ManufactureService {
}
