package com.lpz.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lpz.model.entity.RoomLabel;
import com.lpz.web.admin.service.RoomLabelService;
import com.lpz.web.admin.mapper.RoomLabelMapper;
import org.springframework.stereotype.Service;

/**
* @author lpz
* @description 针对表【room_label(房间&标签关联表)】的数据库操作Service实现
*/
@Service
public class RoomLabelServiceImpl extends ServiceImpl<RoomLabelMapper, RoomLabel>
    implements RoomLabelService{

}




