package com.lpz.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lpz.model.entity.RoomLeaseTerm;
import com.lpz.web.admin.service.RoomLeaseTermService;
import com.lpz.web.admin.mapper.RoomLeaseTermMapper;
import org.springframework.stereotype.Service;

/**
* @author lpz
* @description 针对表【room_lease_term(房间租期管理表)】的数据库操作Service实现
*/
@Service
public class RoomLeaseTermServiceImpl extends ServiceImpl<RoomLeaseTermMapper, RoomLeaseTerm>
    implements RoomLeaseTermService{

}




