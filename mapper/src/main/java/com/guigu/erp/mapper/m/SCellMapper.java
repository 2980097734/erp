package com.guigu.erp.mapper.m;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guigu.erp.domain.m.DFile;
import com.guigu.erp.domain.m.SCell;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SCellMapper extends BaseMapper<SCell> {
    @Select("select * from `d_file` df\n" +
            "left join s_cell sc on df.PRODUCT_ID = sc.PRODUCT_ID\n" +
            "where df.`CHECK_TAG`='S001-1' and sc.check_tag=#{tag} or sc.check_tag=#{tag2}")
    public List<DFile> queryAllSCll(@Param("tag") String tag, @Param("tag2") String tag2);

}
