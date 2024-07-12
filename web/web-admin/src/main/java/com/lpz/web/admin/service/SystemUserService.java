package com.lpz.web.admin.service;

import com.lpz.model.entity.SystemUser;
import com.lpz.web.admin.vo.system.user.SystemUserItemVo;
import com.lpz.web.admin.vo.system.user.SystemUserQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author lpz
* @description 针对表【system_user(员工信息表)】的数据库操作Service
*/
public interface SystemUserService extends IService<SystemUser> {

}
