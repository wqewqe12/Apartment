package com.lpz.web.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lpz.model.entity.RoomInfo;
import com.lpz.web.admin.vo.room.RoomDetailVo;
import com.lpz.web.admin.vo.room.RoomItemVo;
import com.lpz.web.admin.vo.room.RoomQueryVo;
import com.lpz.web.admin.vo.room.RoomSubmitVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author lpz
* @description 针对表【room_info(房间信息表)】的数据库操作Service
*/
public interface RoomInfoService extends IService<RoomInfo> {


    void saveOrUpdateRoom(RoomSubmitVo roomSubmitVo);

    IPage<RoomItemVo> pageItem(Page<RoomItemVo> page, RoomQueryVo queryVo);

    RoomDetailVo getDetailById(Long id);

    void removeRoomById(Long id);
}
