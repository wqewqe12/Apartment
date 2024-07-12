package com.lpz.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lpz.model.entity.SystemPost;
import com.lpz.web.admin.service.SystemPostService;
import com.lpz.web.admin.mapper.SystemPostMapper;
import org.springframework.stereotype.Service;

/**
* @author lpz
* @description 针对表【system_post(岗位信息表)】的数据库操作Service实现
*/
@Service
public class SystemPostServiceImpl extends ServiceImpl<SystemPostMapper, SystemPost>
    implements SystemPostService{

}




