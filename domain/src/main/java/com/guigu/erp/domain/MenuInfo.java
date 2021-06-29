package com.guigu.erp.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@Data
@TableName("sys_menus")
public class MenuInfo {
    @TableId("id")
    private int id;
    private int parentId;
    private int seq;
    private String name;
    private String tip;
    private String descn;
    private String imageUrl;
    private String linkUrl;
    private String target;
    private String status;

    @TableField(exist = false)
    List<MenuInfo> childMenu;

    @TableField(exist = false)
    private boolean checked;
}
