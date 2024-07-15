package com.lpz.web.admin.mapper;

import com.lpz.model.entity.FeeValue;
import com.lpz.web.admin.vo.fee.FeeValueVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author lpz
* @description 针对表【fee_value(杂项费用值表)】的数据库操作Mapper
*/
public interface FeeValueMapper extends BaseMapper<FeeValue> {


    List<FeeValueVo> selectListByApartmentId(Long id);
}




