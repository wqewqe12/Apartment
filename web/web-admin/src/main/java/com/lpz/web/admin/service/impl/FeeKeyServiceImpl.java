package com.lpz.web.admin.service.impl;

import com.lpz.model.entity.FeeKey;
import com.lpz.web.admin.mapper.FeeKeyMapper;
import com.lpz.web.admin.service.FeeKeyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lpz.web.admin.vo.attr.AttrKeyVo;
import com.lpz.web.admin.vo.fee.FeeKeyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author lpz
* @description 针对表【fee_key(杂项费用名称表)】的数据库操作Service实现
*/
@Service
public class FeeKeyServiceImpl extends ServiceImpl<FeeKeyMapper, FeeKey> implements FeeKeyService{

    @Autowired
    private FeeKeyMapper feeKeyMapper;

    @Override
    public List<FeeKeyVo>  listFeeInfo() {
        return feeKeyMapper.listFeeInfo();
    }
}




