package com.lpz.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lpz.model.entity.GraphInfo;
import com.lpz.web.admin.service.GraphInfoService;
import com.lpz.web.admin.mapper.GraphInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author lpz
* @description 针对表【graph_info(图片信息表)】的数据库操作Service实现
*/
@Service
public class GraphInfoServiceImpl extends ServiceImpl<GraphInfoMapper, GraphInfo>
    implements GraphInfoService{

}




