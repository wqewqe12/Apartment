package com.lpz.web.admin.mapper;

import com.lpz.model.entity.AttrValue;
import com.lpz.web.admin.vo.attr.AttrValueVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author lpz
* @description 针对表【attr_value(房间基本属性值表)】的数据库操作Mapper
*/
public interface AttrValueMapper extends BaseMapper<AttrValue> {



    List<AttrValueVo> selectListByRoomId(Long id);
}




