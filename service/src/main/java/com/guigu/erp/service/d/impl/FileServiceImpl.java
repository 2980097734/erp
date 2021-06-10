package com.guigu.erp.service.d.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guigu.erp.domain.d.File;
import com.guigu.erp.mapper.d.FileMapper;
import com.guigu.erp.service.d.FileService;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {
}
