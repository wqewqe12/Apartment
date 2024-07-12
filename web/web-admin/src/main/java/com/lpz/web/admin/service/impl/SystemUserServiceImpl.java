package com.lpz.web.admin.service.impl;

import com.lpz.model.entity.SystemUser;
import com.lpz.web.admin.mapper.SystemUserMapper;
import com.lpz.web.admin.service.SystemUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author lpz
 * @description 针对表【system_user(员工信息表)】的数据库操作Service实现
 */
@Service
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser>
        implements SystemUserService {

}




