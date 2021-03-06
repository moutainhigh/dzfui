package com.dzf.zxkj.platform.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dzf.zxkj.auth.model.sys.CorpModel;
import com.dzf.zxkj.common.lang.DZFBoolean;
import org.apache.ibatis.annotations.Param;


public interface CorpMapper extends BaseMapper<CorpModel> {
    DZFBoolean queryIsChannelByUserName(@Param("username") String username);
    String queryAreaByUserName(@Param("username") String username);
}
