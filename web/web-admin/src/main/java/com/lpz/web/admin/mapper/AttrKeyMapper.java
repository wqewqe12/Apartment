package com.lpz.web.admin.mapper;

import com.lpz.model.entity.AttrKey;
import com.lpz.web.admin.vo.attr.AttrKeyVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author lpz
* @description 针对表【attr_key(房间基本属性表)】的数据库操作Mapper

*/
public interface AttrKeyMapper extends BaseMapper<AttrKey> {

    List<AttrKeyVo> listAttrInfo();
}




