package com.lpz.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lpz.model.entity.RoomPaymentType;
import com.lpz.web.admin.service.RoomPaymentTypeService;
import com.lpz.web.admin.mapper.RoomPaymentTypeMapper;
import org.springframework.stereotype.Service;

/**
* @author lpz
* @description 针对表【room_payment_type(房间&支付方式关联表)】的数据库操作Service实现
*/
@Service
public class RoomPaymentTypeServiceImpl extends ServiceImpl<RoomPaymentTypeMapper, RoomPaymentType>
    implements RoomPaymentTypeService{

}




