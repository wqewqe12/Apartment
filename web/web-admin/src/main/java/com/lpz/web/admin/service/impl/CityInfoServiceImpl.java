package com.lpz.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lpz.model.entity.CityInfo;
import com.lpz.web.admin.service.CityInfoService;
import com.lpz.web.admin.mapper.CityInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author lpz
* @description 针对表【city_info】的数据库操作Service实现
*/
@Service
public class CityInfoServiceImpl extends ServiceImpl<CityInfoMapper, CityInfo>
    implements CityInfoService{

}




