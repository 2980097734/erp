package com.guigu.erp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guigu.erp.mapper.PayMapper;
import com.guigu.erp.domain.Pay;
import com.guigu.erp.service.PayService;
import org.springframework.stereotype.Service;

@Service
public class PayServiceImpl extends ServiceImpl<PayMapper, Pay> implements PayService {
}
