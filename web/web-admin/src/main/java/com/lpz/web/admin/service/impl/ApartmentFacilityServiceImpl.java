package com.lpz.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lpz.model.entity.ApartmentFacility;
import com.lpz.web.admin.service.ApartmentFacilityService;
import com.lpz.web.admin.mapper.ApartmentFacilityMapper;
import org.springframework.stereotype.Service;

/**
* @author lpz
* @description 针对表【apartment_facility(公寓&配套关联表)】的数据库操作Service实现
*/
@Service
public class ApartmentFacilityServiceImpl extends ServiceImpl<ApartmentFacilityMapper, ApartmentFacility>
    implements ApartmentFacilityService{

}




