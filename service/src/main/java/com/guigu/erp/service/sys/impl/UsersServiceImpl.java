package com.guigu.erp.service.sys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guigu.erp.domain.sys.Users;
import com.guigu.erp.mapper.sys.UsersMapper;
import com.guigu.erp.service.sys.UsersService;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

}
