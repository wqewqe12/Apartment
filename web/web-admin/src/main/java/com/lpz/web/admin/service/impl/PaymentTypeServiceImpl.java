package com.lpz.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lpz.model.entity.PaymentType;
import com.lpz.web.admin.service.PaymentTypeService;
import com.lpz.web.admin.mapper.PaymentTypeMapper;
import org.springframework.stereotype.Service;

/**
* @author lpz
* @description 针对表【payment_type(支付方式表)】的数据库操作Service实现
*/
@Service
public class PaymentTypeServiceImpl extends ServiceImpl<PaymentTypeMapper, PaymentType>
    implements PaymentTypeService{

}




