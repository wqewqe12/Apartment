package com.lpz.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lpz.model.entity.FacilityInfo;
import com.lpz.web.admin.service.FacilityInfoService;
import com.lpz.web.admin.mapper.FacilityInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author lpz
* @description 针对表【facility_info(配套信息表)】的数据库操作Service实现
*/
@Service
public class FacilityInfoServiceImpl extends ServiceImpl<FacilityInfoMapper, FacilityInfo>
    implements FacilityInfoService{

}




