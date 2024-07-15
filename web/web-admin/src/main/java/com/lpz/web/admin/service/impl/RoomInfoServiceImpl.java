package com.lpz.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lpz.model.entity.*;
import com.lpz.model.enums.ItemType;
import com.lpz.web.admin.mapper.*;
import com.lpz.web.admin.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lpz.web.admin.vo.attr.AttrValueVo;
import com.lpz.web.admin.vo.graph.GraphVo;
import com.lpz.web.admin.vo.room.RoomDetailVo;
import com.lpz.web.admin.vo.room.RoomItemVo;
import com.lpz.web.admin.vo.room.RoomQueryVo;
import com.lpz.web.admin.vo.room.RoomSubmitVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lpz
 * @description 针对表【room_info(房间信息表)】的数据库操作Service实现
 */
@Service
public class RoomInfoServiceImpl extends ServiceImpl<RoomInfoMapper, RoomInfo> implements RoomInfoService {
    @Autowired
    private GraphInfoService graphInfoService;
    @Autowired
    private RoomAttrValueService roomAttrValueService;
    @Autowired
    private RoomFacilityService roomFacilityService;
    @Autowired
    private RoomLabelService roomLabelService;
    @Autowired
    private RoomPaymentTypeService roomPaymentTypeService;
    @Autowired
    private RoomLeaseTermService roomLeaseTermService;
    @Autowired
    private ApartmentInfoMapper apartmentInfoMapper;
    @Autowired
    private GraphInfoMapper graphInfoMapper;
    @Autowired
    private AttrValueMapper attrValueMapper;
    @Autowired
    private LabelInfoMapper labelInfoMapper;
    @Autowired
    private FacilityInfoMapper facilityInfoMapper;
    @Autowired
    private FeeValueMapper feeValueMapper;
    @Autowired
    private  PaymentTypeMapper paymentTypeMapper;
    @Autowired
    private RoomInfoMapper roomInfoMapper;
    @Autowired
    private LeaseTermMapper leaseTermMapper;
    @Override
    public void saveOrUpdateRoom(RoomSubmitVo roomSubmitVo) {
        boolean isUpdate = roomSubmitVo.getId() != null;
        super.saveOrUpdate(roomSubmitVo);
        //先删除再添加
        //删除图片列表
        LambdaQueryWrapper<GraphInfo> graphQueryWrapper = new LambdaQueryWrapper<>();
        graphQueryWrapper.eq(GraphInfo::getItemType, ItemType.ROOM);
        graphQueryWrapper.eq(GraphInfo::getItemId, roomSubmitVo.getId());
        graphInfoService.remove(graphQueryWrapper);
        //属性信息列表
        LambdaQueryWrapper<RoomAttrValue> attrQueryWapper = new LambdaQueryWrapper<>();
        attrQueryWapper.eq(RoomAttrValue::getRoomId, roomSubmitVo.getId());
        roomAttrValueService.remove(attrQueryWapper);
        //配套信息列表
        LambdaQueryWrapper<RoomFacility> facilityQueryWrapper = new LambdaQueryWrapper<>();
        facilityQueryWrapper.eq(RoomFacility::getRoomId, roomSubmitVo.getId());
        roomFacilityService.remove(facilityQueryWrapper);
        //标签信息列表
        LambdaQueryWrapper<RoomLabel> labelQueryWrapper = new LambdaQueryWrapper<>();
        labelQueryWrapper.eq(RoomLabel::getRoomId,roomSubmitVo.getId());
        roomLabelService.remove(labelQueryWrapper);
        //支付方式列表
        LambdaQueryWrapper<RoomPaymentType> roomPaymentTypeQueryWrapper = new LambdaQueryWrapper<>();
        roomPaymentTypeQueryWrapper.eq(RoomPaymentType::getRoomId,roomSubmitVo.getId());
        roomPaymentTypeService.remove(roomPaymentTypeQueryWrapper);
        //可选租期列表
        LambdaQueryWrapper<RoomLeaseTerm> roomLeaseTermQueryWrapper = new LambdaQueryWrapper<>();
        roomLeaseTermQueryWrapper.eq(RoomLeaseTerm::getRoomId,roomSubmitVo.getId());
        roomLeaseTermService.remove(roomLeaseTermQueryWrapper);

        //添加图片
        List<GraphVo> graphVoList = roomSubmitVo.getGraphVoList();
        if (!CollectionUtils.isEmpty(graphVoList)){
            ArrayList<GraphInfo> graphInfos = new ArrayList<>();
            for (GraphVo graphVo : graphVoList) {
                GraphInfo graphInfo = new GraphInfo();
                graphInfo.setItemType(ItemType.ROOM);
                graphInfo.setItemId(roomSubmitVo.getId());
                graphInfo.setUrl(graphVo.getUrl());
                graphInfo.setName(graphVo.getName());
                graphInfos.add(graphInfo);
            }
            graphInfoService.saveBatch(graphInfos);
        }
       // 添加属性信息
        List<Long> attrValueIds = roomSubmitVo.getAttrValueIds();
        if (!CollectionUtils.isEmpty(attrValueIds)){
            ArrayList<RoomAttrValue> roomAttrValueList = new ArrayList<>();
            for (Long attrValueId : attrValueIds) {
                RoomAttrValue roomAttrValue = RoomAttrValue.builder().
                        roomId(roomSubmitVo.getId()).
                        attrValueId(attrValueId).build();
                roomAttrValueList.add(roomAttrValue);
            }
            roomAttrValueService.saveBatch(roomAttrValueList);
        }

        //添加配套信息列表
        List<Long> facilityInfoIds = roomSubmitVo.getFacilityInfoIds();
        if (!CollectionUtils.isEmpty(facilityInfoIds)) {
            List<RoomFacility> roomFacilityList = new ArrayList<>();
            for (Long facilityInfoId : facilityInfoIds) {
                RoomFacility roomFacility = RoomFacility.builder().roomId(roomSubmitVo.getId()).facilityId(facilityInfoId).build();
                roomFacilityList.add(roomFacility);
            }
            roomFacilityService.saveBatch(roomFacilityList);
        }
        //添加labelInfoList
        List<Long> labelInfoIds = roomSubmitVo.getLabelInfoIds();
        if (!CollectionUtils.isEmpty(labelInfoIds)) {
            ArrayList<RoomLabel> roomLabelList = new ArrayList<>();
            for (Long labelInfoId : labelInfoIds) {
                RoomLabel roomLabel = RoomLabel.builder().roomId(roomSubmitVo.getId()).labelId(labelInfoId).build();
                roomLabelList.add(roomLabel);
            }
            roomLabelService.saveBatch(roomLabelList);
        }
        //添加paymentTypeList
        List<Long> paymentTypeIds = roomSubmitVo.getPaymentTypeIds();
        if (!CollectionUtils.isEmpty(paymentTypeIds)) {
            ArrayList<RoomPaymentType> roomPaymentTypeList = new ArrayList<>();
            for (Long paymentTypeId : paymentTypeIds) {
                RoomPaymentType roomPaymentType = RoomPaymentType.builder().roomId(roomSubmitVo.getId()).paymentTypeId(paymentTypeId).build();
                roomPaymentTypeList.add(roomPaymentType);
            }
            roomPaymentTypeService.saveBatch(roomPaymentTypeList);
        }

        //添加leaseTermList
        List<Long> leaseTermIds = roomSubmitVo.getLeaseTermIds();
        if (!CollectionUtils.isEmpty(leaseTermIds)) {
            ArrayList<RoomLeaseTerm> roomLeaseTerms = new ArrayList<>();
            for (Long leaseTermId : leaseTermIds) {
                RoomLeaseTerm roomLeaseTerm = RoomLeaseTerm.builder().roomId(roomSubmitVo.getId()).leaseTermId(leaseTermId).build();
                roomLeaseTerms.add(roomLeaseTerm);
            }
            roomLeaseTermService.saveBatch(roomLeaseTerms);
        }
    }

    @Override
    public IPage<RoomItemVo> pageItem(Page<RoomItemVo> page, RoomQueryVo queryVo) {
        return roomInfoMapper.pageRoomItemByQuery(page,queryVo);
    }

    @Override
    public RoomDetailVo getDetailById(Long id) {
        RoomInfo roomInfo = roomInfoMapper.selectById(id);
        ApartmentInfo apartmentInfo = apartmentInfoMapper.selectById(roomInfo.getApartmentId());
        List<GraphVo> graphVoList = graphInfoMapper.selectListByItemTypeAndId(ItemType.ROOM, id);
        List<AttrValueVo> attrValueList = attrValueMapper.selectListByRoomId(id);
        List<FacilityInfo> facilityInfoList = facilityInfoMapper.selectListByRoomId(id);
        List<LabelInfo> labelInfoList = labelInfoMapper.selectListByRoomId(id);
        List<PaymentType> paymentTypeList = paymentTypeMapper.selectListByRoomId(id);
        List<LeaseTerm> leaseTermList = leaseTermMapper.selectListByRoomId(id);

        RoomDetailVo adminRoomDetailVo = new RoomDetailVo();
        BeanUtils.copyProperties(roomInfo, adminRoomDetailVo);
        adminRoomDetailVo.setApartmentInfo(apartmentInfo);
        adminRoomDetailVo.setGraphVoList(graphVoList);
        adminRoomDetailVo.setAttrValueVoList(attrValueList);
        adminRoomDetailVo.setFacilityInfoList(facilityInfoList);
        adminRoomDetailVo.setLabelInfoList(labelInfoList);
        adminRoomDetailVo.setPaymentTypeList(paymentTypeList);
        adminRoomDetailVo.setLeaseTermList(leaseTermList);
        return adminRoomDetailVo;
    }

    @Override
    public void removeRoomById(Long id) {
        super.removeById(id);
        //删除Vo的额外属性,图片等等
        LambdaQueryWrapper<GraphInfo> graphQueryWrapper = new LambdaQueryWrapper<>();
        graphQueryWrapper.eq(GraphInfo::getItemType, ItemType.ROOM);
        graphQueryWrapper.eq(GraphInfo::getItemId, id);
        graphInfoService.remove(graphQueryWrapper);
        //属性信息列表
        LambdaQueryWrapper<RoomAttrValue> attrQueryWapper = new LambdaQueryWrapper<>();
        attrQueryWapper.eq(RoomAttrValue::getRoomId, id);
        roomAttrValueService.remove(attrQueryWapper);
        //配套信息列表
        LambdaQueryWrapper<RoomFacility> facilityQueryWrapper = new LambdaQueryWrapper<>();
        facilityQueryWrapper.eq(RoomFacility::getRoomId, id);
        roomFacilityService.remove(facilityQueryWrapper);
        //标签信息列表
        LambdaQueryWrapper<RoomLabel> labelQueryWrapper = new LambdaQueryWrapper<>();
        labelQueryWrapper.eq(RoomLabel::getRoomId,id);
        roomLabelService.remove(labelQueryWrapper);
        //支付方式列表
        LambdaQueryWrapper<RoomPaymentType> roomPaymentTypeQueryWrapper = new LambdaQueryWrapper<>();
        roomPaymentTypeQueryWrapper.eq(RoomPaymentType::getRoomId,id);
        roomPaymentTypeService.remove(roomPaymentTypeQueryWrapper);
        //可选租期列表
        LambdaQueryWrapper<RoomLeaseTerm> roomLeaseTermQueryWrapper = new LambdaQueryWrapper<>();
        roomLeaseTermQueryWrapper.eq(RoomLeaseTerm::getRoomId,id);
        roomLeaseTermService.remove(roomLeaseTermQueryWrapper);
    }
}




