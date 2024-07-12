package com.lpz.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lpz.model.entity.AttrValue;
import com.lpz.web.admin.service.AttrValueService;
import com.lpz.web.admin.mapper.AttrValueMapper;
import org.springframework.stereotype.Service;

/**
* @author lpz
* @description 针对表【attr_value(房间基本属性值表)】的数据库操作Service实现
*/
@Service
public class AttrValueServiceImpl extends ServiceImpl<AttrValueMapper, AttrValue>
    implements AttrValueService{

}




