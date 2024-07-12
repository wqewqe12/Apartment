package com.lpz.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lpz.model.entity.ApartmentFeeValue;
import com.lpz.web.admin.service.ApartmentFeeValueService;
import com.lpz.web.admin.mapper.ApartmentFeeValueMapper;
import org.springframework.stereotype.Service;

/**
* @author lpz
* @description 针对表【apartment_fee_value(公寓&杂费关联表)】的数据库操作Service实现
*/
@Service
public class ApartmentFeeValueServiceImpl extends ServiceImpl<ApartmentFeeValueMapper, ApartmentFeeValue>
    implements ApartmentFeeValueService{

}




