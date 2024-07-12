package com.lpz.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lpz.model.entity.ApartmentLabel;
import com.lpz.web.admin.service.ApartmentLabelService;
import com.lpz.web.admin.mapper.ApartmentLabelMapper;
import org.springframework.stereotype.Service;

/**
* @author lpz
* @description 针对表【apartment_label(公寓标签关联表)】的数据库操作Service实现
*/
@Service
public class ApartmentLabelServiceImpl extends ServiceImpl<ApartmentLabelMapper, ApartmentLabel>
    implements ApartmentLabelService{

}




