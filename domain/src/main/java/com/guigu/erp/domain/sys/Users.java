package com.guigu.erp.domain.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_users")
public class Users {
    private int id;
    private String loginId;
    private String password;
    private String status;
    private String photoPath;
}
