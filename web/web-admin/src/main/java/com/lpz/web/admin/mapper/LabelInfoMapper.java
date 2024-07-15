package com.lpz.web.admin.mapper;

import com.lpz.model.entity.LabelInfo;
import com.lpz.model.enums.ItemType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author lpz
* @description 针对表【label_info(标签信息表)】的数据库操作Mapper
* @Entity com.lpz.model.LabelInfo
*/
public interface LabelInfoMapper extends BaseMapper<LabelInfo> {

    List<LabelInfo> selectListByApartmentId(Long id);
}




