package com.lpz.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lpz.common.exception.LeaseException;
import com.lpz.common.result.ResultCodeEnum;
import com.lpz.model.entity.*;
import com.lpz.model.enums.ItemType;
import com.lpz.web.admin.mapper.*;
import com.lpz.web.admin.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lpz.web.admin.vo.apartment.ApartmentDetailVo;
import com.lpz.web.admin.vo.apartment.ApartmentItemVo;
import com.lpz.web.admin.vo.apartment.ApartmentQueryVo;
import com.lpz.web.admin.vo.apartment.ApartmentSubmitVo;
import com.lpz.web.admin.vo.fee.FeeValueVo;
import com.lpz.web.admin.vo.graph.GraphVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lpz
 * @description 针对表【apartment_info(公寓信息表)】的数据库操作Service实现
 */
@Service
public class ApartmentInfoServiceImpl extends ServiceImpl<ApartmentInfoMapper, ApartmentInfo> implements ApartmentInfoService {
    @Autowired
    private GraphInfoService graphInfoService;
    @Autowired
    private ApartmentFacilityService apartmentFacilityService;
    @Autowired
    private ApartmentLabelService apartmentLabelService;
    @Autowired
    private ApartmentFeeValueService apartmentFeeValueService;
    @Autowired
    private ApartmentInfoMapper apartmentInfoMapper;
    @Autowired
    private GraphInfoMapper graphInfoMapper;
    @Autowired
    private LabelInfoMapper labelInfoMapper;
    @Autowired
    private FacilityInfoMapper facilityInfoMapper;
    @Autowired
    private FeeValueMapper feeValueMapper;
    @Autowired
    RoomInfoMapper roomInfoMapper;
    @Override
    public void saveOrUpdateApartment(ApartmentSubmitVo apartmentSubmitVo) {
        boolean isUpdate = apartmentSubmitVo.getId() != null;
        super.saveOrUpdate(apartmentSubmitVo);
        if (isUpdate){
            //先删除。后添加
            //删除图片列表
            LambdaQueryWrapper<GraphInfo>  graphQueryWrapper = new LambdaQueryWrapper<>();
            graphQueryWrapper.eq(GraphInfo::getItemType, ItemType.APARTMENT);
            graphQueryWrapper.eq(GraphInfo::getItemId, apartmentSubmitVo.getId());
            graphInfoService.remove(graphQueryWrapper);
            //删除配套列表
            LambdaQueryWrapper<ApartmentFacility>  facilityQueryWrapper = new LambdaQueryWrapper<>();
            facilityQueryWrapper.eq(ApartmentFacility::getApartmentId, apartmentSubmitVo.getId());
            apartmentFacilityService.remove(facilityQueryWrapper);
            //删除标签列表
            LambdaQueryWrapper<ApartmentLabel>  labelQueryWrapper = new LambdaQueryWrapper<>();
            labelQueryWrapper.eq(ApartmentLabel::getApartmentId, apartmentSubmitVo.getId());
            apartmentLabelService.remove(labelQueryWrapper);
            //删除杂费列表
            LambdaQueryWrapper<ApartmentFeeValue>  feeValueQueryWrapper = new LambdaQueryWrapper<>();
            feeValueQueryWrapper.eq(ApartmentFeeValue::getApartmentId, apartmentSubmitVo.getId());
            apartmentFeeValueService.remove(feeValueQueryWrapper);
        }
        //插入图片
        List<GraphVo> graphVoList = apartmentSubmitVo.getGraphVoList();
        //判空
        if (!CollectionUtils.isEmpty(graphVoList)){
            ArrayList<GraphInfo> graphInfoList = new ArrayList<>();
            for (GraphVo graphVo : graphVoList) {
                GraphInfo graphInfo = new GraphInfo();
                graphInfo.setItemType(ItemType.APARTMENT);
                graphInfo.setItemId(apartmentSubmitVo.getId());
                graphInfo.setName(graphVo.getName());
                graphInfo.setUrl(graphVo.getUrl());
                graphInfoList.add(graphInfo);
            }
            graphInfoService.saveBatch(graphInfoList);
        }
        //插入配套
        List<Long> facilityInfoIds = apartmentSubmitVo.getFacilityInfoIds();
        if (!CollectionUtils.isEmpty(facilityInfoIds)){
            ArrayList<ApartmentFacility> apartmentFacilityList = new ArrayList<>();
            for (Long facilityId : facilityInfoIds) {
                ApartmentFacility apartmentFacility = new ApartmentFacility();
                apartmentFacility.setApartmentId(apartmentSubmitVo.getId());
                apartmentFacility.setFacilityId(facilityId);
                apartmentFacilityList.add(apartmentFacility);
            }
            apartmentFacilityService.saveBatch(apartmentFacilityList);
        }

        //插入标签列表
        List<Long> labelIds = apartmentSubmitVo.getLabelIds();
        if (!CollectionUtils.isEmpty(labelIds)) {
            List<ApartmentLabel> apartmentLabelList = new ArrayList<>();
            for (Long labelId : labelIds) {
                ApartmentLabel apartmentLabel = new ApartmentLabel();
                apartmentLabel.setApartmentId(apartmentSubmitVo.getId());
                apartmentLabel.setLabelId(labelId);
                apartmentLabelList.add(apartmentLabel);
            }
            apartmentLabelService.saveBatch(apartmentLabelList);
        }

        //插入杂费
        List<Long> feeValueIds = apartmentSubmitVo.getLabelIds();
        if (!CollectionUtils.isEmpty(feeValueIds)) {
            List<ApartmentFeeValue> apartmentFeeValueList = new ArrayList<>();
            for (Long feeValueId : feeValueIds) {
                ApartmentFeeValue apartmentFeeValue = new ApartmentFeeValue();
                apartmentFeeValue.setApartmentId(apartmentSubmitVo.getId());
                apartmentFeeValue.setFeeValueId(feeValueId);
                apartmentFeeValueList.add(apartmentFeeValue);
            }
            apartmentFeeValueService.saveBatch(apartmentFeeValueList);
        }

    }

    @Override
    public IPage<ApartmentItemVo> pageItem(Page<ApartmentItemVo> page, ApartmentQueryVo queryVo) {

        return apartmentInfoMapper.pageItem(page,queryVo);
    }

    @Override
    public ApartmentDetailVo getDetailById(Long id) {
        //查询公寓
        ApartmentInfo apartmentInfo = apartmentInfoMapper.selectById(id);
        //查询图片列表
        List<GraphVo> graphVoList = graphInfoMapper.selectListByItemTypeAndId(ItemType.APARTMENT,id);
        //查询杂费列表
        List<FeeValueVo> feeValueVoList = feeValueMapper.selectListByApartmentId(id);
        //查询标签列表
        List<LabelInfo> labelInfoList = labelInfoMapper.selectListByApartmentId(id);
        //查询配套列表
        List<FacilityInfo> facilityInfoList = facilityInfoMapper.selectListByApartmentId(id);

        ApartmentDetailVo apartmentDetailVo = new ApartmentDetailVo();
        BeanUtils.copyProperties(apartmentInfo,apartmentDetailVo);
        apartmentDetailVo.setGraphVoList(graphVoList);
        apartmentDetailVo.setFeeValueVoList(feeValueVoList);
        apartmentDetailVo.setLabelInfoList(labelInfoList);
        apartmentDetailVo.setFacilityInfoList(facilityInfoList);
        return apartmentDetailVo;
    }

    @Override
    public void removeApartmentById(Long id) {
        LambdaQueryWrapper<RoomInfo> roomQueryWrapper = new LambdaQueryWrapper<>();
        roomQueryWrapper.eq(RoomInfo::getApartmentId,id);
        Long count = roomInfoMapper.selectCount(roomQueryWrapper);
        if (count >0){
            //终止删除，返回提示信息
            throw new LeaseException(ResultCodeEnum.ADMIN_APARTMENT_DELETE_ERROR);
        }

        super.removeById(id);
        //删除图片列表
        LambdaQueryWrapper<GraphInfo>  graphQueryWrapper = new LambdaQueryWrapper<>();
        graphQueryWrapper.eq(GraphInfo::getItemType, ItemType.APARTMENT);
        graphQueryWrapper.eq(GraphInfo::getItemId, id);
        graphInfoService.remove(graphQueryWrapper);
        //删除配套列表
        LambdaQueryWrapper<ApartmentFacility>  facilityQueryWrapper = new LambdaQueryWrapper<>();
        facilityQueryWrapper.eq(ApartmentFacility::getApartmentId, id);
        apartmentFacilityService.remove(facilityQueryWrapper);
        //删除标签列表
        LambdaQueryWrapper<ApartmentLabel>  labelQueryWrapper = new LambdaQueryWrapper<>();
        labelQueryWrapper.eq(ApartmentLabel::getApartmentId, id);
        apartmentLabelService.remove(labelQueryWrapper);
        //删除杂费列表
        LambdaQueryWrapper<ApartmentFeeValue>  feeValueQueryWrapper = new LambdaQueryWrapper<>();
        feeValueQueryWrapper.eq(ApartmentFeeValue::getApartmentId, id);
        apartmentFeeValueService.remove(feeValueQueryWrapper);
    }
}




