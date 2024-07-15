package com.lpz.web.admin.mapper;

import com.lpz.model.entity.GraphInfo;
import com.lpz.model.enums.ItemType;
import com.lpz.web.admin.vo.graph.GraphVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author lpz
* @description 针对表【graph_info(图片信息表)】的数据库操作Mapper
* @Entity com.lpz.model.GraphInfo
*/
public interface GraphInfoMapper extends BaseMapper<GraphInfo> {

    List<GraphVo> selectListByItemTypeAndId(ItemType itemType, Long id);
}




