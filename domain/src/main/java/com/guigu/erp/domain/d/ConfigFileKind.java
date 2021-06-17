package com.guigu.erp.domain.d;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@Data
@TableName("d_config_file_kind")
public class ConfigFileKind {
    @TableId("id")
    private Integer id;
    private Double pId;
    private String kindId;
    private String kindName;
    private Double kindLevel;

    @TableField(exist = false)
    public List<ConfigFileKind> childConfig;
}
