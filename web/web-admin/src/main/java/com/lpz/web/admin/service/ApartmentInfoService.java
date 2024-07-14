package com.lpz.web.admin.service;

import com.lpz.model.entity.ApartmentInfo;
import com.lpz.web.admin.vo.apartment.ApartmentDetailVo;
import com.lpz.web.admin.vo.apartment.ApartmentItemVo;
import com.lpz.web.admin.vo.apartment.ApartmentQueryVo;
import com.lpz.web.admin.vo.apartment.ApartmentSubmitVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author lpz
* @description 针对表【apartment_info(公寓信息表)】的数据库操作Service
*/
public interface ApartmentInfoService extends IService<ApartmentInfo> {

    void saveOrUpdateApartment(ApartmentSubmitVo apartmentSubmitVo);
}
