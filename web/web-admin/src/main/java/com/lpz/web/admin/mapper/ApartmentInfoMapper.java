package com.lpz.web.admin.mapper;

import com.lpz.model.entity.ApartmentInfo;
import com.lpz.model.enums.LeaseStatus;
import com.lpz.web.admin.vo.apartment.ApartmentItemVo;
import com.lpz.web.admin.vo.apartment.ApartmentQueryVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
* @author lpz
* @description 针对表【apartment_info(公寓信息表)】的数据库操作Mapper
*/
public interface ApartmentInfoMapper extends BaseMapper<ApartmentInfo> {

}




