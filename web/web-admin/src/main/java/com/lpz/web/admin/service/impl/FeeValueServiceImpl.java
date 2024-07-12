package com.lpz.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lpz.model.entity.FeeValue;
import com.lpz.web.admin.service.FeeValueService;
import com.lpz.web.admin.mapper.FeeValueMapper;
import org.springframework.stereotype.Service;

/**
* @author lpz
* @description 针对表【fee_value(杂项费用值表)】的数据库操作Service实现
*/
@Service
public class FeeValueServiceImpl extends ServiceImpl<FeeValueMapper, FeeValue>
    implements FeeValueService{

}




