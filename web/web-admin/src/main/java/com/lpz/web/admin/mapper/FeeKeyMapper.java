package com.lpz.web.admin.mapper;

import com.lpz.model.entity.FeeKey;
import com.lpz.web.admin.vo.fee.FeeKeyVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author lpz
* @description 针对表【fee_key(杂项费用名称表)】的数据库操作Mapper
* @Entity com.lpz.model.FeeKey
*/
public interface FeeKeyMapper extends BaseMapper<FeeKey> {


    List<FeeKeyVo> listFeeInfo();
}




